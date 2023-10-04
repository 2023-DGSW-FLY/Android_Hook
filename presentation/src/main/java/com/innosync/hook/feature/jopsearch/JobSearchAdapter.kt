package com.innosync.hook.feature.jopsearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.innosync.data.repository.model.JobSearchModel
import com.innosync.hook.databinding.JopOfferItemBinding
import com.innosync.hook.databinding.JopSearchItemBinding

class JobSearchAdapter constructor(
    private val itemList: List<JobSearchModel>
): RecyclerView.Adapter<JobSearchAdapter.viewHolder>() {

    inner class viewHolder(private val binding: JopSearchItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(JobSearchModel: JobSearchModel){
            binding.textJobComment.text = JobSearchModel.detail
            binding.textUserName.text = JobSearchModel.userName
            binding.textTime.text = JobSearchModel.time
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder(JopSearchItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)).apply {

        }
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
    }

}