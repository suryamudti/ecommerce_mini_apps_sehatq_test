package id.surya.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.gson.Gson
import id.surya.auth.databinding.AuthFragmentBinding
import id.surya.l_extras.base.BaseFragment
import id.surya.l_extras.data.model.MedSocSignIn
import id.surya.l_extras.ext.getViewModel
import id.surya.l_extras.ext.navigatorImplicit
import id.surya.l_extras.ext.putArgs
import id.surya.l_extras.ext.showToast
import id.surya.l_extras.util.Preference
import id.surya.ecommerce.AppConst.GOOGLE_SIGN_IN_DATA
import id.surya.ecommerce.AppConst.RC_SIGN_IN
import id.surya.ecommerce.AppNavigator.getHomeRoute
import id.surya.ecommerce.MainApp
import kotlinx.android.synthetic.main.auth_fragment.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONException


/**
 * references :
 * https://androidclarified.com/android-facebook-login-example/ [facebook login]
 */
class AuthFragment : BaseFragment<AuthFragmentBinding, AuthVM>(), View.OnClickListener {

    override fun onClick(v: View?) {


        when (v?.id) {
            R.id.btn_auth_signIn -> {
                navigateToHome()
            }
            R.id.btn_auth_facebook -> onFacebookSignIn()
            else -> onGoogleSignIn()
        }
    }

    override fun bindLayoutRes() = R.layout.auth_fragment

    override fun onSetViewModel(): AuthVM {
        return getViewModel { AuthVM(MainApp.instance) }
    }

    override fun onLoadObserver(baseViewModel: AuthVM) {

    }

    override fun onStartWork() {
        setupViewListener()
        setupFacebookSignIn()

        // check current state if user has been login
        if (Preference.getPref(requireContext(), GOOGLE_SIGN_IN_DATA).isNotEmpty()) {
            navigateToHome()
        }
    }

    override fun onSetInstrument() {
        baseViewModel.let {
            viewBinding.apply {
                authVM = it
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                account?.let {
                    firebaseAuthWithGoogle(it)
                }
            } catch (e: ApiException) {
                Log.e(AuthFragment::class.java.simpleName, "Google sign in failed", e)
            }
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        user?.let {
                            showUserAuthProfile(it)
                        }
                    } else {
                        requireContext().showToast("Authentication Failed")
                    }
                }
    }

    private fun showUserAuthProfile(user: FirebaseUser) {
        GlobalScope.launch {
            btn_auth_signIn.text = user.email

            val medSocSignIn = MedSocSignIn(
                    fullname = user.displayName ?: "",
                    email = user.email ?: "",
                    profileImage = getCurrentUser()?.photoUrl.toString(),
                    phoneNumber = user.phoneNumber ?: ""
            )
            saveGoogleSignIn(medSocSignIn)

            delay(1000)
            navigateToHome()
        }
    }

    private fun saveGoogleSignIn(data: MedSocSignIn) {
        val dataAsString = Gson().toJson(data)
        Preference.savePref(requireContext(), GOOGLE_SIGN_IN_DATA, dataAsString)
    }

    private fun setupViewListener() {
        btn_auth_signIn.setOnClickListener(this)
        btn_auth_facebook.setOnClickListener(this)
        btn_auth_google.setOnClickListener(this)
    }

    private fun navigateToHome() {
        requireContext().navigatorImplicit(getHomeRoute()) {}
        activity?.finish()
    }

    private fun onGoogleSignIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    fun onGoogleSignOut() {
        FirebaseAuth.getInstance().signOut()
        googleSignInClient.signOut()
                .addOnCompleteListener(requireActivity()) {
                    if (getCurrentUser() == null) {
                        btn_auth_signIn.text = "Sign In"
                    }
                }
                .addOnFailureListener { exception ->
                    requireContext().showToast(exception.message
                            ?: "There is something wrong with sign out process")
                }
    }

    fun onFacebookSignOut() {
        LoginManager.getInstance().logOut()
    }

    private fun setupFacebookSignIn() {
        callbackManager = CallbackManager.Factory.create()

        btn_auth_fbOriginal.apply {
            setReadPermissions("email", "public_profile")
            fragment = this@AuthFragment
            registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    handleFacebookAccessToken(loginResult.accessToken)
                }

                override fun onCancel() {
                }

                override fun onError(error: FacebookException) {
                }
            })
        }
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        val request = GraphRequest.newMeRequest(
                token
        ) { facebookObj, response ->
            try {
                val name = facebookObj.getString("name")
                val email = facebookObj.getString("email")
                val profileImage = facebookObj.getJSONObject("picture").getJSONObject("data").getString("url")
                btn_auth_signIn.text = name

                val medSocSignIn = MedSocSignIn(
                        fullname = name,
                        email = email,
                        profileImage = profileImage,
                        phoneNumber = getCurrentUser()?.phoneNumber ?: ""
                )
                saveGoogleSignIn(medSocSignIn)

                GlobalScope.launch {
                    delay(1000)
                    navigateToHome()
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        val parameters = Bundle()
        parameters.putString("fields", "id,name,email,picture.width(200)")
        request.parameters = parameters
        // Initiate the GraphRequest
        request.executeAsync()
    }

    private fun checkUserState() {
        if (Preference.getPrefBoolean(requireContext(), IS_GOOGLE_SELECTED)) {
            Log.d("IRFAN ", "GOOGLE")
            getCurrentUser()?.let {
                showUserAuthProfile(it)
            }
        } else {
            Log.d("IRFAN ", "FB")
            val accessToken = AccessToken.getCurrentAccessToken()
            if (accessToken != null) {
                handleFacebookAccessToken(accessToken)
            } else {
                btn_auth_signIn.text = "Sign In"
            }
        }
    }

    private fun getCurrentUser() = FirebaseAuth.getInstance().currentUser

    private fun getUserProfile() {
        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            // Name, email address, and profile photo Url
            val name = user.displayName
            val email = user.email
            val photoUrl = user.photoUrl

            // Check if user's email is verified
            val emailVerified = user.isEmailVerified

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            val uid = user.uid
        }
    }

    private fun onFacebookSignIn() {
        btn_auth_fbOriginal.performClick()
    }

    companion object {
        const val IS_GOOGLE_SELECTED = "IS_GOOGLE_SELECTED"

        fun newInstance() = AuthFragment().putArgs { }
    }

}