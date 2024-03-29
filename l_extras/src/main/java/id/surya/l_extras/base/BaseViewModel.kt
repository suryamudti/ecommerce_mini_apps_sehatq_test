package id.surya.l_extras.base

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableField
import id.surya.l_extras.data.AppRepository
import id.surya.l_extras.util.Injection
import id.surya.l_extras.util.SingleLiveEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    var eventShowProgress = SingleLiveEvent<Boolean>()
    var eventGlobalMessage = SingleLiveEvent<String>()
    var verticalList = ObservableField(0)
    var horizontalList = ObservableField(1)
    val repository: AppRepository by lazy {
        Injection.provideAppRepository(application)
    }

    private val parentJob = Job()
    private val coroutinesContext: CoroutineContext
        get() = parentJob + Dispatchers.IO

    val scope = CoroutineScope(coroutinesContext)

    open fun startWork() {}

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

}