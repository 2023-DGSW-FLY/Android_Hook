package com.innosync.hook.feature.chat

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.innosync.domain.model.RoomModel
import com.innosync.hook.R
import com.innosync.hook.databinding.ItemChatBinding
import com.innosync.hook.util.getYour
import com.innosync.hook.util.toImageUrl

class ChatRvAdaptor constructor(
    private val my: String,
    private val rooms: List<RoomModel>,
    private val context: Context,
    private val users: Map<String, String>?,
    private val action: (RoomModel) -> Unit,
): RecyclerView.Adapter<ChatRvAdaptor.ViewHolder>() {

    inner class ViewHolder(binding: ItemChatBinding): RecyclerView.ViewHolder(binding.root) {
        val thumbnail = binding.icUser
        val name = binding.textUserName
        val message = binding.textUserContent
        val newImage = binding.imageNew
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)).apply {
            itemView.setOnClickListener {
                val room  = rooms[adapterPosition]
                action(room)
            }
        }
    }

    override fun getItemCount(): Int = rooms.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = rooms[position]
        holder.message.text = item.lastMessage
//        holder.name.text = my
        val your = item.getYour(my)
        if (users == null) {
            holder.name.text = your
        } else {
            holder.name.text = users[your] ?: ""
        }
        Log.d("TAG", "onBindViewHolder: $your")
        if (item.users?.get(my) == false) {
            holder.newImage.visibility = View.VISIBLE
        }
        Glide.with(context)
            .load(your.toImageUrl())
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)
            .into(holder.thumbnail)
//        holder.thumbnail

    }
}