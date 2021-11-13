package viktor.khlebnikov.gb.history

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
//import org.koin.androidx.viewmodel.ext.android.viewModel
import viktor.khlebnikov.gb.core.BaseActivity
import viktor.khlebnikov.gb.history.databinding.ActivityHistoryBinding
import viktor.khlebnikov.gb.history.description.DescriptionActivity
import viktor.khlebnikov.gb.model.AppState
import viktor.khlebnikov.gb.model.DataModel

class HistoryActivity : BaseActivity<AppState, HistoryInteractor>() {

    private val binding by lazy { ActivityHistoryBinding.inflate(layoutInflater) }

    private val adapter by lazy { HistoryAdapter(onListItemClickListener) }

    override val model by viewModel<HistoryViewModel>()
//    override val model = ViewModelProvider(this).get(HistoryViewModel::class.java)


    private val onListItemClickListener: HistoryAdapter.OnListItemClickListener =
        object : HistoryAdapter.OnListItemClickListener {
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