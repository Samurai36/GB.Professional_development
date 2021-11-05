package viktor.khlebnikov.gb.gbprofrazrab.translator.data.local.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import viktor.khlebnikov.gb.gbprofrazrab.databinding.ActivityMainRecyclerviewItemBinding
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.DataModel
import viktor.khlebnikov.gb.gbprofrazrab.translator.ui.main.MainAdapter

class HistoryAdapter(
    private val onListItemClickListener: MainAdapter.OnListItemClickListener
) : ListAdapter<DataModel, HistoryAdapter.HistoryViewHolder>(MainCallback) {

    private var data: List<DataModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    inner class HistoryViewHolder(view: ViewGroup) :
        RecyclerView.ViewHolder(
            ActivityMainRecyclerviewItemBinding.inflate(
                view.inflater(),
                view,
                false
            ).root
        ) {

        fun bind(data: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                val binder = ActivityMainRecyclerviewItemBinding.bind(itemView)
                binder.headerTextviewRecyclerItem.text = data.text
                binder.descriptionTextviewRecyclerItem.text =
                    data.meanings?.firstOrNull()?.translation?.translation
                binder.root.setOnClickListener {
                    openInNewWindow(data)
                }
            }
        }

        private fun openInNewWindow(listItemData: DataModel) {
            onListItemClickListener.onItemClick(listItemData)
        }
    }


    private fun ViewGroup.inflater() = LayoutInflater.from(context)


    object MainCallback : DiffUtil.ItemCallback<DataModel>() {
        override fun areItemsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
            return oldItem == newItem
        }

    }
}
