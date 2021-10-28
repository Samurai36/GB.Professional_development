package viktor.khlebnikov.gb.gbprofrazrab.translator.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import io.reactivex.observers.DisposableObserver
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.AppState
import viktor.khlebnikov.gb.gbprofrazrab.translator.interactor.main.MainInteractor
import viktor.khlebnikov.gb.gbprofrazrab.translator.ui.base.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val interactor: MainInteractor
) : BaseViewModel<AppState>() {

    fun getWordDescriptions(word: String, isOnline: Boolean) {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { stateLiveData.value = AppState.Loading(null) }
                .subscribeWith(getObserver())
        )
    }
    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {

            override fun onNext(appState: AppState) {
                stateLiveData.value = appState
            }

            override fun onError(e: Throwable) {
                stateLiveData.value = AppState.Error(e)
            }

            override fun onComplete() {
            }
        }
    }

}