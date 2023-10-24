package com.innosync.hook.feature.jopsearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.innosync.hook.databinding.JopSearchItemBinding

class JobSearchAdapter constructor(
    private val itemList: List<JobSearchRvModel>
): RecyclerView.Adapter<JobSearchAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: JopSearchItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(JobSearchRvModel: JobSearchRvModel){
            binding.textJobComment.text = JobSearchRvModel.detail
            binding.textUserName.text = JobSearchRvModel.userName
            binding.textTime.text = JobSearchRvModel.time
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(JopSearchItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)).apply {

        }
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
    }
}