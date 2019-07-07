package id.surya.product_detail

import id.surya.l_extras.base.BaseViewModel
import id.surya.l_extras.data.model.MovieFilter
import id.surya.ecommerce.MainApp
import id.surya.l_extras.data.model.ProductPromo
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProductDetailVM(mainApp: MainApp) : BaseViewModel(mainApp) {


    fun insertProduct(product: ProductPromo) {
        scope.launch {
            eventShowProgress.postValue(true)
            repository.insertProduct(product)
            delay(1000)
            if (repository.getAllLocalProducts()?.isNotEmpty()!!) {
                eventGlobalMessage.postValue("Payment is Success")
                delay(1000)
                eventShowProgress.postValue(false)
            } else {
                eventGlobalMessage.postValue("Payment is Failed")
                delay(1000)
                eventShowProgress.postValue(false)
            }
        }
    }

}