package viktor.khlebnikov.gb.gbprofrazrab.translator.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.AppState
import viktor.khlebnikov.gb.gbprofrazrab.translator.rx.ISchedulerProvider
import viktor.khlebnikov.gb.gbprofrazrab.translator.rx.SchedulerProvider

abstract class BaseViewModel<T : AppState>(
    protected val stateLiveData: MutableLiveData<T> = MutableLiveData(),
    protected val schedulerProvider: ISchedulerProvider = SchedulerProvider()
) : ViewModel() {

    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun getStateLiveData(): LiveData<T> = stateLiveData

    override fun onCleared() {
        compositeDisposable.clear()
    }
}