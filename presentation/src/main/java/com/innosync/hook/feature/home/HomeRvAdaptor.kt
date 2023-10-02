package com.innosync.hook.feature.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.innosync.domain.model.HackathonModel
import com.innosync.hook.databinding.ItemJobOpeningBinding
import com.innosync.hook.databinding.ItemJobSearchBinding

class HomeRvAdaptor constructor(
    private val item: List<HomeRvData>
): RecyclerView.Adapter<HomeRvAdaptor.ViewHolder>() {
    sealed class ViewHolder(
        binding: ViewBinding
    ): RecyclerView.ViewHolder(binding.root) {

        abstract fun bind(item: HomeRvData)

        class JobOpeningHolder(
            private val binding: ItemJobOpeningBinding
        ): ViewHolder(binding) {
            override fun bind(item: HomeRvData) {
                binding.textJobStatus.text = if (item.status == "matching") "매칭중" else "매칭완료"
                binding.textView.text = item.writer
                binding.textJobComment.text = item.title
            }
        }
        class JobSearchHolder(
            private val binding: ItemJobSearchBinding
        ): ViewHolder(binding) {
            override fun bind(item: HomeRvData) {
                binding.textJobStatus.text = if (item.status == "matching") "매칭중" else "매칭완료"
                binding.textUserName.text = item.writer
                binding.textJobComment.text = item.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == 0) {
            ViewHolder.JobSearchHolder(ItemJobSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        } else {
            ViewHolder.JobOpeningHolder(ItemJobOpeningBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun getItemCount(): Int = item.size

    override fun getItemViewType(position: Int): Int = item[position].type

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item[position])
    }

}