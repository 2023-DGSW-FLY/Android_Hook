package com.innosync.hook.feature.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.innosync.domain.model.AlarmModel
import com.innosync.hook.R
import com.innosync.hook.databinding.ItemAlarmBinding
import com.innosync.hook.databinding.ItemCongressInfoBinding

class HomeAlarmRvAdaptor constructor(
    val item: List<AlarmModel>,
    val action: (AlarmModel) -> Unit
): RecyclerView.Adapter<HomeAlarmRvAdaptor.ViewHolder>() {
    inner class ViewHolder(val binding: ItemAlarmBinding): RecyclerView.ViewHolder(binding.root) {
        val content = binding.textContent
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemAlarmBinding.inflate(LayoutInflater.from(parent.context), parent, false)).apply {
            itemView.setOnClickListener {
                action(item[adapterPosition])
            }
        }

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = item[position]
        if (item.type == "m") {
            holder.content.text = "'${item.content}' 님이 채팅을 보냈어요!"
        }
        if (item.type == "p") {
            holder.content.text = "'${item.content}' 님이 구인에 지원하셨어요!"
        }
    }
}