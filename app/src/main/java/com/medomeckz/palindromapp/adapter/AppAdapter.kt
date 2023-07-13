package com.medomeckz.palindromapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.medomeckz.palindromapp.data.model.DataItem
import com.medomeckz.palindromapp.databinding.ItemUserBinding
import com.medomeckz.palindromapp.ui.second.SecondActivity

class AppAdapter: PagingDataAdapter<DataItem, AppAdapter.ViewHolder>(DIFF_CALLBACK) {
    class ViewHolder(private val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(data: DataItem) {
            with(binding) {
                tvFirst.text = data.firstName
                tvLast.text = data.lastName
                tvEmail.text = data.email

                Glide.with(itemView)
                    .load(data.avatar)
                    .centerCrop()
                    .into(cvUser)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, SecondActivity::class.java)
                    intent.putExtra(SecondActivity.NAME, "${data.firstName} ${data.lastName}")
                    it.context.startActivity(intent)
                }
            }
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        if(data != null) holder.bind(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    companion object {
        val DIFF_CALLBACK = object: DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }
}