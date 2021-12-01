package ru.android.history.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.android.history.databinding.ItemWordBinding
import ru.android.models.DataModel

class HistoryAdapter : ListAdapter<DataModel, HistoryAdapter.HistoryViewHolder>(HistoryCallBack) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryViewHolder {
        return HistoryViewHolder(parent)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class HistoryViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        ItemWordBinding.inflate(parent.inflate(), parent, false).root
    ) {
        fun bind(data: DataModel) {
            val binder = ItemWordBinding.bind(itemView)

            binder.headerTextviewRecyclerItem.text = data.text
            binder.descriptionTextviewRecyclerItem.text =
                data.meaning?.firstOrNull()?.translation?.translation
        }
    }

    private fun ViewGroup.inflate() = LayoutInflater.from(context)
}

object HistoryCallBack : DiffUtil.ItemCallback<DataModel>() {
    override fun areItemsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
        return oldItem == newItem
    }

}