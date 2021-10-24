package viktor.khlebnikov.gb.gbprofrazrab.translator.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import viktor.khlebnikov.gb.gbprofrazrab.databinding.ActivityMainRecyclerviewItemBinding
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.DataModel

class MainAdapter(
    private val onListItemClickListener: OnListItemClickListener,
    private var data: List<DataModel>
) : ListAdapter<DataModel, MainAdapter.MainViewHolder>(MainCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(parent)
    }

    //если не переопределить этот метод, в mainActivity адаптер не успевает заататчится
    // и его, как вы сделали на уроке, приходится переносить в oncreate
    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(data[position])
//        holder.bind(currentList[position]) - раз переопределили getItemCount, то здесь уже берем позицию из data
    }


    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    inner class MainViewHolder(view: ViewGroup) :
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
                binder.root.setOnClickListener { openInNewWindow(data)
                }
            }
        }
    }

    private fun ViewGroup.inflater() = LayoutInflater.from(context)

    private fun openInNewWindow(listItemData: DataModel) {
        onListItemClickListener.onItemClick(listItemData)
    }

    interface OnListItemClickListener {
        fun onItemClick(data: DataModel)
    }

}

object MainCallback : DiffUtil.ItemCallback<DataModel>() {
    override fun areItemsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
        return oldItem == newItem
    }

}

