package ru.android.mytranslator.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.android.models.DataModel
import ru.android.mytranslator.databinding.ItemWordBinding

class MainAdapter(
    private val itemClickListener: (DataModel) -> Unit,
) : ListAdapter<DataModel, MainAdapter.MainViewHolder>(MainCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(parent)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class MainViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        ItemWordBinding.inflate(parent.inflate(), parent, false).root
    ) {
        fun bind(data: DataModel) {
            val binder = ItemWordBinding.bind(itemView)

            binder.headerTextviewRecyclerItem.text = data.text
            binder.descriptionTextviewRecyclerItem.text =
                data.meaning?.firstOrNull()?.translation?.translation
            binder.root.setOnClickListener { itemClickListener(data) }
        }
    }

    private fun ViewGroup.inflate() = LayoutInflater.from(context)

}

object MainCallBack : DiffUtil.ItemCallback<DataModel>() {
    override fun areItemsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
        return oldItem == newItem
    }

}