package id.surya.home

import android.support.design.widget.BottomNavigationView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import id.surya.home.databinding.HomeActivityBinding
import id.surya.home.master.MasterFragment
import id.surya.home.news.NewsFragment
import id.surya.home.profile.ProfileFragment
import id.surya.home.transaction.TransactionFragment
import id.surya.l_extras.base.BaseActivity
import id.surya.l_extras.ext.gone
import id.surya.l_extras.ext.navigatorImplicit
import id.surya.l_extras.ext.replaceFragmentInActivity
import id.surya.l_extras.ext.visible
import id.surya.ecommerce.AppNavigator.getProductSearchRoute
import kotlinx.android.synthetic.main.home_activity.*

class HomeActivity : BaseActivity<HomeActivityBinding>(),
    BottomNavigationView.OnNavigationItemSelectedListener,
    View.OnClickListener {

    private var actionToolbarMenu: Menu? = null

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.lin_toolbar_searchContainer -> navigatorImplicit(getProductSearchRoute()) {}
        }
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        return when (p0.itemId) {
            R.id.action_home -> {
                replaceFragmentInActivity(MasterFragment.newInstance(), R.id.frame_container)
                setupLikeButtonVisibility(true)
                setupSearchContainerVisibility(true)
                setupToolbarTitleVisibility(false, "")
                setupActionToolbarMenuVisibility(true)
                true
            }
            else -> {
                replaceFragmentInActivity(TransactionFragment.newInstance(), R.id.frame_container)
                setupLikeButtonVisibility(false)
                setupSearchContainerVisibility(false)
                setupToolbarTitleVisibility(true, "Transaction Products")
                setupActionToolbarMenuVisibility(false)
                true
            }
        }
    }

    override fun bindLayoutRes() = R.layout.home_activity

    override fun bindToolbarId() = R.id.toolbar

    override fun bindRootFragment() = MasterFragment.newInstance()

    override fun bindFragmentContainerId() = R.id.frame_container

    override fun onStartWork() {
        setupViewListener()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_home_menu, menu)
        actionToolbarMenu = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when {
        item?.itemId == android.R.id.home -> {
            true
        }
        else -> false
    }

    private fun setupViewListener() {
        bottom_home.setOnNavigationItemSelectedListener(this)
        lin_toolbar_searchContainer.setOnClickListener(this)
    }

    private fun setupSearchContainerVisibility(isVisible: Boolean) {
        if (isVisible) {
            lin_toolbar_searchContainer.visible()
        } else {
            lin_toolbar_searchContainer.gone()
        }
    }

    private fun setupLikeButtonVisibility(isVisible: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(isVisible)
    }

    private fun setupToolbarTitleVisibility(isVisible: Boolean, title: String) {
        if (isVisible) {
            txt_toolbar_title.apply {
                visible()
                text = title
            }
        } else {
            txt_toolbar_title.apply {
                gone()
                text = title
            }
        }
    }

    private fun setupActionToolbarMenuVisibility(isVisible: Boolean) {
        actionToolbarMenu?.findItem(R.id.action_bookmark)?.isVisible = isVisible
    }

}
