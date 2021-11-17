package viktor.khlebnikov.gb.core

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import viktor.khlebnikov.gb.core.databinding.LoadingLayoutBinding
import viktor.khlebnikov.gb.core.viewmodel.BaseViewModel
import viktor.khlebnikov.gb.core.viewmodel.Interactor
import viktor.khlebnikov.gb.model.AppState
import viktor.khlebnikov.gb.model.usersData.DataModel
import viktor.khlebnikov.gb.utils.OnlineLiveData
import viktor.khlebnikov.gb.utils.ui.AlertDialogFragment

private const val DIALOG_FRAGMENT_TAG = "DIALOG_FRAGMENT_TAG"

abstract class BaseActivity<T : AppState, I : Interactor<T>> : AppCompatActivity() {

    private lateinit var binding: LoadingLayoutBinding
    protected abstract val model: BaseViewModel<T>

    protected var isNetworkAvailable: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        subscribeToNetworkChange()
    }

    private fun subscribeToNetworkChange() {
        OnlineLiveData(this).observe(
            this@BaseActivity,
        ) {
            isNetworkAvailable = it
            if (!isNetworkAvailable) {
                Toast.makeText(
                    this@BaseActivity,
                    R.string.dialog_message_device_is_offline,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding = LoadingLayoutBinding.inflate(layoutInflater)
        if (!isNetworkAvailable && isDialogNull()) {
            showNoInternetConnectionDialog()
        }
    }

    protected fun renderData(appState: T) {
        when (appState) {
            is AppState.Success -> {
                showViewWorking()
                appState.data?.let {
                    if (it.isEmpty()) {
                        showAlertDialog(
                            getString(R.string.dialog_tittle_sorry),
                            getString(R.string.empty_server_response_on_success)
                        )
                    } else {
                        setDataToAdapter(it)
                    }
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    binding.progressBarHorizontal.visibility = View.VISIBLE
                    binding.progressBarRound.visibility = View.GONE
                    binding.progressBarHorizontal.progress = appState.progress!!
                } else {
                    binding.progressBarHorizontal.visibility = View.GONE
                    binding.progressBarRound.visibility = View.VISIBLE
                }
            }
            is AppState.Error -> {
                showViewWorking()
                showAlertDialog(getString(R.string.error_textview_stub), appState.error.message)
            }
        }
    }

    protected fun showNoInternetConnectionDialog() {
        showAlertDialog(
            getString(R.string.dialog_title_device_is_offline),
            getString(R.string.dialog_message_device_is_offline)
        )
    }

    protected fun showAlertDialog(title: String?, message: String?) {
        AlertDialogFragment.newInstance(title, message)
            .show(supportFragmentManager, DIALOG_FRAGMENT_TAG)
    }

    private fun showViewWorking() {
        binding.loadingFrameLayout.visibility = View.GONE
    }

    private fun showViewLoading() {
        binding.loadingFrameLayout.visibility = View.VISIBLE
    }

    private fun isDialogNull(): Boolean {
        return supportFragmentManager.findFragmentByTag(DIALOG_FRAGMENT_TAG) == null
    }

    abstract fun setDataToAdapter(data: List<DataModel>)

}