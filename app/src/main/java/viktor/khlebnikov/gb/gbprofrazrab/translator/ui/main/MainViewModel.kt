package viktor.khlebnikov.gb.gbprofrazrab.translator.ui.main

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.AppState
import viktor.khlebnikov.gb.gbprofrazrab.translator.interactor.main.MainInteractor
import viktor.khlebnikov.gb.gbprofrazrab.translator.ui.base.BaseViewModel

class MainViewModel(
    private val interactor: MainInteractor
) : BaseViewModel<AppState>() {

    private val liveDataForViewToObserve: LiveData<AppState> = getStateLiveData()

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    fun getWordDescriptions(word: String, isOnline: Boolean) {
        stateLiveData.value = AppState.Loading(null)
        cancelJob()
        viewModelScope.launch {
            startInteractor(word, isOnline)
        }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) =
        withContext(Dispatchers.IO) {
            stateLiveData.postValue(interactor.getData(word, isOnline))

        }

    override fun handleError(error: Throwable) {
        stateLiveData.value = AppState.Error(error)
    }
}
