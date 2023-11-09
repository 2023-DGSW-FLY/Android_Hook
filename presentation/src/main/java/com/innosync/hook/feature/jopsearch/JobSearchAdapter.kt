package com.innosync.hook.feature.jopsearch

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.innosync.data.util.User.get
import com.innosync.hook.R
import com.innosync.hook.databinding.JopSearchItemBinding

class JobSearchAdapter constructor(
    private val itemList: List<JobSearchRvModel>,
    private val context: Context,
    private val action: (JobSearchRvModel) -> Unit
): RecyclerView.Adapter<JobSearchAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: JopSearchItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(JobSearchRvModel: JobSearchRvModel, position: Int){
            binding.textJobComment.text = JobSearchRvModel.detail
            binding.textUserName.text = JobSearchRvModel.userName
            binding.textTime.text = JobSearchRvModel.time

            if (position%2 == 1) {
                binding.layoutItem.background = context.getDrawable(R.drawable.shape_gray4)
            }
            if (JobSearchRvModel.status) {
                binding.textJobStatus.text = "매칭중"

            } else {
                binding.textJobStatus.text = "매칭완료"
                binding.layoutItem.alpha = 0.3f
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(JopSearchItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)).apply {
            itemView.setOnClickListener {
                val item = itemList[adapterPosition]
                if (item.status) {
                    action(item)
                }
            }
        }
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item, position)
    }
}