package viktor.khlebnikov.gb.gbprofrazrab.translator.data.local.history

import androidx.lifecycle.LiveData
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.AppState
import viktor.khlebnikov.gb.gbprofrazrab.translator.ui.base.BaseViewModel
import viktor.khlebnikov.gb.gbprofrazrab.translator.utils.parseLocalSearchResults

class HistoryViewModel(private val interactor: HistoryInteractor) : BaseViewModel<AppState>() {

    fun subscribe(): LiveData<AppState> {
        return stateLiveData
    }

    override fun getData(word: String, isOnline: Boolean) {
        stateLiveData.value = AppState.Loading(null)
        cancelJob()
        viewModelScope.launch {
            val data = parseLocalSearchResults(interactor.getData(word, isOnline))
            stateLiveData.value = data
        }
    }

    override fun onCleared() {
        stateLiveData.value = AppState.Success(null)
        viewModelScope.coroutineContext.cancelChildren()
    }

    override fun handleError(error: Throwable) {
        stateLiveData.postValue(AppState.Error(error))
    }
}