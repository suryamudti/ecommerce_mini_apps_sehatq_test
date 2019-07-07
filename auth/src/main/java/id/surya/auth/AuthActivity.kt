package id.surya.auth

import id.surya.auth.databinding.AuthActivityBinding
import id.surya.l_extras.base.BaseActivity

class AuthActivity : BaseActivity<AuthActivityBinding>() {

    override fun bindLayoutRes() = R.layout.auth_activity

    override fun bindToolbarId() = 0

    override fun bindRootFragment() = AuthFragment.newInstance()

    override fun bindFragmentContainerId() = R.id.frame_container

    override fun onStartWork() {
        // fb hash debug
        // 5FNGN5RkhNnbATbmy2bu9gJy4Uc=

        // fb hash release
        // ldxBbqqTnL8ZiI62E/cNsuA/dVI=
    }

}
