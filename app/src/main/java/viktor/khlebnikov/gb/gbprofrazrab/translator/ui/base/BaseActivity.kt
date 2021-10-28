package viktor.khlebnikov.gb.gbprofrazrab.translator.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.AppState
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.Presenter
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.View

abstract class BaseActivity<T : AppState> : AppCompatActivity() {

    abstract val model: BaseViewModel<T>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model.getStateLiveData().observe(this) { renderData(it) }
    }

    abstract fun renderData(appState: T)

}