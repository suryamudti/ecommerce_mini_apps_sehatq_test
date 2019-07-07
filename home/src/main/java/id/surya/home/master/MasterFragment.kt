package id.surya.home.master

import android.arch.lifecycle.Observer
import com.google.gson.Gson
import id.surya.home.R
import id.surya.home.databinding.MasterFragmentBinding
import id.surya.l_extras.base.BaseFragment
import id.surya.l_extras.data.model.MovieFilter
import id.surya.l_extras.ext.*
import id.surya.ecommerce.AppConst.MOVIE_ITEM
import id.surya.ecommerce.AppConst.PRODUCT_ITEM
import id.surya.ecommerce.AppNavigator.getProductDetailRoute
import id.surya.ecommerce.MainApp
import id.surya.l_extras.data.model.Category
import id.surya.l_extras.data.model.ProductPromo
import kotlinx.android.synthetic.main.master_fragment.*

class MasterFragment : BaseFragment<MasterFragmentBinding, MasterVM>(), MasterItemCallback {


    override fun onItemProductSelected(product: ProductPromo) {
        requireContext().apply {
            showToast("You choose ${product.title} movie")
            navigatorImplicit(getProductDetailRoute()) {
                putExtra(PRODUCT_ITEM, Gson().toJson(product))
            }
        }
    }

    override fun onCategoryMovieSelected(genre: String) {
        requireContext().showToast("You choose $genre movie")
    }

    override fun onItemMovieSelected(movieFilter: MovieFilter) {
        requireContext().apply {
            showToast("You choose ${movieFilter.title} movie")
            navigatorImplicit(getProductDetailRoute()) {
                putExtra(PRODUCT_ITEM, Gson().toJson(movieFilter))
            }
        }
    }

    override fun bindLayoutRes() = R.layout.master_fragment

    override fun onSetViewModel(): MasterVM {
        return getViewModel { MasterVM(MainApp.instance) }
    }

    override fun onLoadObserver(baseViewModel: MasterVM) {

        baseViewModel.apply {
            productEvent.observe(this@MasterFragment, Observer { data->
                data?.let {
                    setupMastersList(it)
                }

            })
            categoryEvent.observe(this@MasterFragment, Observer { data ->
                data?.let {
                    setupCategoryList(it)
                }
            })
        }
    }

    override fun onStartWork() {
    }

    override fun onSetInstrument() {
        baseViewModel.let {
            viewBinding.apply {
                masterVM = it
            }
        }
    }

    private fun setupCategoryList(data: List<Category>){
        rec_master_category.apply {
            horizontalListStyle()
            adapter = CategoryAdapter(data)
        }
    }

    private fun setupMastersList(data: List<ProductPromo>) {

        rec_master.apply {
            verticalListStyle()
            adapter = MasterAdapter(data, this@MasterFragment)
        }


    }


    companion object {
        fun newInstance() = MasterFragment().putArgs { }
    }
}