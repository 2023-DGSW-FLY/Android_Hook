package com.innosync.hook.feature.mybox.detail

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.innosync.hook.databinding.ItemApplicantsBinding

class ApplicantsAdapter(
    private val itemList: List<ApplicantsRvModel>,
    private val action: (ApplicantsRvModel) -> Unit
): RecyclerView.Adapter<ApplicantsAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding:ItemApplicantsBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: ApplicantsRvModel, position: Int){
            binding.applicantsName.text = item.name
            if (position % 2 == 1){
                binding.layoutItem.setBackgroundColor(Color.parseColor("#F3F3F3"))
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemApplicantsBinding.inflate(LayoutInflater.from(parent.context), parent, false)).apply {
            itemView.setOnClickListener {
                action(itemList[adapterPosition])
            }

        }
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item, position)
    }

}