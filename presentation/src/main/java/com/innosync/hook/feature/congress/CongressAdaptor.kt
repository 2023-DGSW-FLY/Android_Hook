package com.innosync.hook.feature.congress

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.innosync.domain.model.CongressModel
import com.innosync.hook.R
import com.innosync.hook.databinding.ItemChatBinding
import com.innosync.hook.databinding.ItemCongressBinding

class CongressAdaptor constructor(
    private val item: List<CongressModel>,
    private val context: Context
): RecyclerView.Adapter<CongressAdaptor.ViewHolder>() {
    inner class ViewHolder(binding: ItemCongressBinding): RecyclerView.ViewHolder(binding.root) {
        val thumbnail = binding.imageCongress
        val title = binding.textCongressImageTitle
        val date = binding.textCongressImageDate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemCongressBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = item[position]
        with(holder) {
            Glide.with(context)
                .load(item.imgUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.shape_message_gray)
                .into(thumbnail)
            title.text = item.title
            date.text = item.dateTime
        }
    }
}