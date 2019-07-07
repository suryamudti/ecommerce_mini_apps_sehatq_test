package id.surya.home.master

import android.util.Log
import id.surya.l_extras.base.BaseViewModel
import id.surya.l_extras.data.model.MovieFilter
import id.surya.l_extras.util.SingleLiveEvent
import id.surya.ecommerce.MainApp
import id.surya.l_extras.data.model.Category
import id.surya.l_extras.data.model.ProductPromo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MasterVM(mainApp: MainApp) : BaseViewModel(mainApp) {

    val productEvent = SingleLiveEvent<List<ProductPromo>>()
    val categoryEvent = SingleLiveEvent<List<Category>>()

    init {
        getProductList()
    }

    override fun onCleared() {
        super.onCleared()
        getProductList().cancel()
    }

    private fun getProductList() = scope.launch {
        eventShowProgress.postValue(true)
        val productDataResponse = repository.getAllProducts()

        withContext(Dispatchers.Main){
            val productList = mutableListOf<ProductPromo>()
            val categoryList = mutableListOf<Category>()

            productList.apply {
                clear()
                productDataResponse?.productPromo?.filterIndexed { index, productPromo ->
                    add(
                        ProductPromo(
                            id = null,
                            title = productPromo.title,
                            description = productPromo.description,
                            loved = productPromo.loved,
                            price = productPromo.price,
                            imageUrl = productPromo.imageUrl
                        )
                    )
                }
            }
            categoryList.apply {
                clear()
                productDataResponse?.category?.filterIndexed { index, category ->
                    add(category)
                }
            }

            categoryEvent.value = categoryList
            productEvent.value = productList

            eventShowProgress.value = false

        }
    }
}