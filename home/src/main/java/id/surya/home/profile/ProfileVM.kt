package id.surya.home.profile

import id.surya.l_extras.base.BaseViewModel
import id.surya.l_extras.util.SingleLiveEvent
import id.surya.ecommerce.MainApp

class ProfileVM(mainApp: MainApp) : BaseViewModel(mainApp) {

    val imageUrl = SingleLiveEvent<String>()
    val fullname = SingleLiveEvent<String>()
    val email = SingleLiveEvent<String>()

}
