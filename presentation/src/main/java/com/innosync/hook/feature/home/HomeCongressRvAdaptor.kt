package com.innosync.hook.feature.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.innosync.hook.R
import com.innosync.hook.databinding.ItemCongressInfoBinding

class HomeCongressRvAdaptor constructor(
    val item: List<String>,
    val context: Context,
    val action: () -> Unit
): RecyclerView.Adapter<HomeCongressRvAdaptor.ViewHolder>() {
    inner class ViewHolder(val binding: ItemCongressInfoBinding): RecyclerView.ViewHolder(binding.root) {
        val thumbnail = binding.imageCongressInfo
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemCongressInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)).apply {
            itemView.setOnClickListener {
                action()
            }
        }

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load(item[position])
            .placeholder(R.drawable.shape_dark_gray)
            .error(R.drawable.ic_launcher_foreground)
            .into(holder.thumbnail)
    }
}