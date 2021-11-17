package viktor.khlebnikov.gb.gbprofrazrab.translator.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.android.ext.android.getKoin
import viktor.khlebnikov.gb.core.BaseActivity
import viktor.khlebnikov.gb.gbprofrazrab.R
import viktor.khlebnikov.gb.gbprofrazrab.translator.interactor.main.MainInteractor
import viktor.khlebnikov.gb.history.HistoryActivity
import viktor.khlebnikov.gb.history.convertMeaningsToString
import viktor.khlebnikov.gb.history.description.DescriptionActivity
import viktor.khlebnikov.gb.model.AppState
import viktor.khlebnikov.gb.model.usersData.DataModel
import viktor.khlebnikov.gb.repository.SearchDialogFragment
import viktor.khlebnikov.gb.utils.viewByID

class MainActivity : BaseActivity<AppState, MainInteractor>() {

//    private lateinit var binding: ActivityMainBinding

    private val mainActivityRecyclerview by viewByID<RecyclerView>(R.id.main_activity_recyclerview)

    private val searchFab by viewByID<FloatingActionButton>(R.id.search_fab)

    private val adapter: MainAdapter by lazy { MainAdapter(onListItemClickListener) }

    private val mainActivityScope =
        getKoin().getOrCreateScope<MainActivity>("MainActivityScope")

    override lateinit var model: MainViewModel

    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                if (isNetworkAvailable) {
                    startActivity(
                        DescriptionActivity.getIntent(
                            this@MainActivity,
                            data.text,
                            convertMeaningsToString(data.meanings),
                            data.meanings.firstOrNull()?.imageUrl
                        )
                    )
                } else {
                    showNoInternetConnectionDialog()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.getDefaultNightMode())
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
        setContentView(R.layout.activity_main)
        iniViewModel()
        initViews()
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivityScope.close()
    }

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }

    private fun iniViewModel() {
        if (mainActivityRecyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        val viewModel: MainViewModel by mainActivityScope.inject()
        model = viewModel
        model.subscribe().observe(this@MainActivity) { renderData(it) }
    }

    private fun initViews() {
        val decoration = DividerItemDecoration(
            applicationContext,
            LinearLayoutManager.VERTICAL
        )
        ContextCompat.getDrawable(baseContext, R.drawable.decorate)
            ?.let { decoration.setDrawable(it) }
        mainActivityRecyclerview.addItemDecoration(decoration)

        searchFab.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(object :
                SearchDialogFragment.OnSearchClickListener {
                override fun onClick(searchWord: String) {
                    model.getData(searchWord, true)
                }
            })
            searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }
        mainActivityRecyclerview.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_history, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_history -> {
                startActivity(HistoryActivity.createIntent(this))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }
}

