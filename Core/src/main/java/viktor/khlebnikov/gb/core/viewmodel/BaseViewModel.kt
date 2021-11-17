package viktor.khlebnikov.gb.core.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import viktor.khlebnikov.gb.model.AppState

abstract class BaseViewModel<T : AppState>(
    protected val stateLiveData: MutableLiveData<T> = MutableLiveData()
) : ViewModel() {

    protected val viewModelScope = CoroutineScope(
        Dispatchers.Main + SupervisorJob() + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        }
    )

    fun getStateLiveData(): LiveData<T> = stateLiveData

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }
    protected fun cancelJob() {
        viewModelScope.coroutineContext.cancelChildren()
    }

    abstract fun handleError(error: Throwable)
    abstract fun getData(word: String, isOnline: Boolean)
}