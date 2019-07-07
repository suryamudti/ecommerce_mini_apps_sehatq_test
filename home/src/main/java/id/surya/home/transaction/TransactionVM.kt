package id.surya.home.transaction

import id.surya.l_extras.base.BaseViewModel
import id.surya.l_extras.data.model.MovieFilter
import id.surya.l_extras.util.SingleLiveEvent
import id.surya.ecommerce.MainApp
import id.surya.l_extras.data.model.ProductPromo
import kotlinx.coroutines.launch

class TransactionVM(mainApp: MainApp) : BaseViewModel(mainApp) {

    val checkSizeEvent = SingleLiveEvent<Boolean>()
    var productListListEvent = SingleLiveEvent<List<ProductPromo>>()

    init {
        scope.launch {
            checkSizeEvent.postValue(repository.getAllLocalProducts()?.isNotEmpty())

            val moviesFilter = repository.getAllLocalProducts()
            if (moviesFilter?.isNotEmpty()!!) productListListEvent.postValue(moviesFilter)
        }
    }
}