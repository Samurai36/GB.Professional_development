package viktor.khlebnikov.gb.gbprofrazrab.translator.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.AppState

abstract class BaseActivity<T : AppState> : AppCompatActivity() {

    protected abstract val model: BaseViewModel<T>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model.getStateLiveData().observe(this) { renderData(it) }
    }

    abstract fun renderData(appState: T)

}