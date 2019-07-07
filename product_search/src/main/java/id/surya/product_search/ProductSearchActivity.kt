package id.surya.product_search

import id.surya.l_extras.base.BaseActivity
import id.surya.product_search.databinding.ProductSearchActivityBinding

class ProductSearchActivity : BaseActivity<ProductSearchActivityBinding>() {

    override fun bindLayoutRes() = R.layout.product_search_activity

    override fun bindToolbarId() = 0

    override fun bindRootFragment() = ProductSearchFragment.newInstance()

    override fun bindFragmentContainerId() = R.id.frame_container

    override fun onStartWork() {

    }

}
