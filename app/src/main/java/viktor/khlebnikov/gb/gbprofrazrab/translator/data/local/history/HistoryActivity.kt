package viktor.khlebnikov.gb.gbprofrazrab.translator.data.local.history

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import viktor.khlebnikov.gb.gbprofrazrab.R
import viktor.khlebnikov.gb.gbprofrazrab.databinding.ActivityHistoryBinding
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.AppState
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.DataModel
import viktor.khlebnikov.gb.gbprofrazrab.translator.isOnline
import viktor.khlebnikov.gb.gbprofrazrab.translator.ui.base.BaseActivity
import viktor.khlebnikov.gb.gbprofrazrab.translator.ui.description.DescriptionActivity
import viktor.khlebnikov.gb.gbprofrazrab.translator.ui.main.MainAdapter

class HistoryActivity : BaseActivity<AppState, HistoryInteractor>() {

    private val binding by lazy { ActivityHistoryBinding.inflate(layoutInflater) }

    private val adapter by lazy { HistoryAdapter(onListItemClickListener) }

    override val model by viewModel<HistoryViewModel>()

    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                startActivity(
                    DescriptionActivity.getIntent(
                        this@HistoryActivity,
                        data.text.orEmpty(),
                        data.meanings?.joinToString { it.translation?.translation.orEmpty() }
                            .orEmpty(),
                        data.meanings?.firstOrNull()?.imageUrl
                    )
                )
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        iniViewModel()
        initViews()
    }

    override fun onResume() {
        super.onResume()
        model.getData("", false)
    }

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }

    private fun iniViewModel() {
        if (binding.historyActivityRecyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        model.subscribe().observe(this@HistoryActivity) { renderData(it) }
    }

    private fun initViews() {
        val decoration = DividerItemDecoration(
            applicationContext,
            LinearLayoutManager.VERTICAL
        )
        ContextCompat.getDrawable(baseContext, R.drawable.decorate)
            ?.let { decoration.setDrawable(it) }
        binding.historyActivityRecyclerview.adapter = HistoryAdapter(onListItemClickListener)
        binding.historyActivityRecyclerview.addItemDecoration(decoration)
        binding.historyActivityRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.historyActivityRecyclerview.adapter = adapter
    }

    companion object {
        fun createIntent(context: Context) = Intent(context, HistoryActivity::class.java)
    }
}