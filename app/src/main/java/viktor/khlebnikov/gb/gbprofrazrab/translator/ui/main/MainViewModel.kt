package viktor.khlebnikov.gb.gbprofrazrab.translator.ui.main

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import viktor.khlebnikov.gb.gbprofrazrab.translator.interactor.main.MainInteractor
import viktor.khlebnikov.gb.history.parseOnlineSearchResults
import viktor.khlebnikov.gb.model.AppState

class MainViewModel(
    private val interactor: MainInteractor
) : viktor.khlebnikov.gb.core.viewmodel.BaseViewModel<AppState>() {

    private val liveDataForViewToObserve: LiveData<AppState> = getStateLiveData()

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) =
        withContext(Dispatchers.IO) {
            stateLiveData.postValue(parseOnlineSearchResults(interactor.getData(word, isOnline)))

        }

    override fun handleError(error: Throwable) {
        stateLiveData.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        stateLiveData.value = AppState.Success(null)
        super.onCleared()
    }

    override fun getData(word: String, isOnline: Boolean) {
        stateLiveData.value = AppState.Loading(null)
        cancelJob()
        viewModelScope.launch {
            startInteractor(word, isOnline)
        }
    }
}
