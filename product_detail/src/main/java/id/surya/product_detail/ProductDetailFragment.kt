package id.surya.product_detail

import android.arch.lifecycle.Observer
import android.graphics.Rect
import android.support.v4.content.ContextCompat
import com.google.gson.Gson
import id.surya.l_extras.base.BaseFragment
import id.surya.l_extras.data.model.MovieFilter
import id.surya.l_extras.ext.getViewModel
import id.surya.l_extras.ext.putArgs
import id.surya.ecommerce.AppConst.MOVIE_ITEM
import id.surya.ecommerce.AppConst.PRODUCT_ITEM
import id.surya.ecommerce.MainApp
import id.surya.l_extras.data.model.ProductPromo
import id.surya.product_detail.databinding.ProductDetailFragmentBinding
import kotlinx.android.synthetic.main.product_detail_fragment.*
import id.surya.l_extras.R as extrasR

class ProductDetailFragment : BaseFragment<ProductDetailFragmentBinding, ProductDetailVM>() {

//    private lateinit var movieAsObject: MovieFilter
    private lateinit var productAsObject: ProductPromo
    private lateinit var productDetailVM: ProductDetailVM

    override fun bindLayoutRes() = R.layout.product_detail_fragment

    override fun onSetViewModel(): ProductDetailVM {
        return getViewModel { ProductDetailVM(MainApp.instance) }
    }

    override fun onLoadObserver(baseViewModel: ProductDetailVM) {
        productDetailVM = baseViewModel.apply {
            eventShowProgress.observe(this@ProductDetailFragment, Observer { state ->
                state?.let {
                    if (!it) requireActivity().finish()
                }
            })
        }
    }

    override fun onStartWork() {
        try {
            val movieAsString = requireActivity().intent.getStringExtra(PRODUCT_ITEM)
            productAsObject = Gson().fromJson<ProductPromo>(movieAsString, ProductPromo::class.java)
            viewBinding.apply {
                price = productAsObject.price
                description = productAsObject.description
                imageUrl = productAsObject.imageUrl
            }
        } catch (e: Exception) {
        }

        setupViewListener()
    }

    override fun onSetInstrument() {

    }

    private fun setupViewListener() {
        scrollview_productdetail.viewTreeObserver.addOnScrollChangedListener {
            val scrollBounds = Rect()
            scrollview_productdetail.getHitRect(scrollBounds)

            val productDetailActivity = (requireActivity() as ProductDetailActivity)
            val cardContainerProductDetail = productDetailActivity.getCardContainerProductDetail()
            if (frame_productDetail_imageBackdrop.getLocalVisibleRect(scrollBounds)) {
                cardContainerProductDetail.apply {
                    setCardBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.transparent))
                    cardElevation = CARD_EVEVATION_DEFAULT
                }
            } else {
                cardContainerProductDetail.apply {
                    setCardBackgroundColor(ContextCompat.getColor(requireContext(), extrasR.color.colorPrimaryDark))
                    cardElevation = CARD_ELEVATION
                }
            }
        }
        btn_productDetail_submit.setOnClickListener {


            val buyProduct = ProductPromo(
                id = null,
                title = productAsObject.title,
                description = productAsObject.description,
                price = productAsObject.price,
                imageUrl = productAsObject.imageUrl,
                loved = productAsObject.loved
            )

            productDetailVM.insertProduct(buyProduct)
        }
    }

    companion object {
        const val CARD_ELEVATION = 4f
        const val CARD_EVEVATION_DEFAULT = 0f

        fun newInstance() = ProductDetailFragment().putArgs { }
    }

}