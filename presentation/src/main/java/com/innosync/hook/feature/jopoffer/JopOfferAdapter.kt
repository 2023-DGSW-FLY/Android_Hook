package com.innosync.hook.feature.jopoffer


import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.innosync.hook.R
import com.innosync.hook.feature.jopoffer.model.JobOfferModel
import com.innosync.hook.databinding.JopOfferItemBinding


class JopOfferAdapter constructor(
    private val itemList: List<JobOfferModel>,
    private val action: (JobOfferModel) -> Unit
//    private val action: (Int) -> Unit
): RecyclerView.Adapter<JopOfferAdapter.viewHolder>() {
    inner class viewHolder(private val binding: JopOfferItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(JobOfferModel: JobOfferModel, position: Int){
            binding.time.text = JobOfferModel.time
            binding.userName.text = JobOfferModel.userName
            binding.competitionName.text = JobOfferModel.competitionName
            binding.technology.text = JobOfferModel.technology
            if (position%2 == 1) {
                binding.layoutItem.setBackgroundColor(Color.parseColor("#F3F3F3"))
            }
            // 이미지
            Glide.with(binding.profileImg)
                .load(JobOfferModel.img)
                .into(binding.profileImg)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder(JopOfferItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)).apply {
            itemView.setOnClickListener{
                val item = itemList[adapterPosition]
                action(item)
            }
        }
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item, position)


    }

    override fun getItemCount(): Int = itemList.size


}


