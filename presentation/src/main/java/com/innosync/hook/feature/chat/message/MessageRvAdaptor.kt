package com.innosync.hook.feature.chat.message

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.innosync.domain.model.ChatModel
import com.innosync.hook.R
import com.innosync.hook.databinding.ItemLeftBinding
import com.innosync.hook.databinding.ItemRightBinding
import com.innosync.hook.util.toStringDate


class MessageRvAdaptor constructor(
    private val context: Context,
    private val item: List<ChatModel>,
    private val my: String,
    private val targetThumbnail: String
): RecyclerView.Adapter<MessageRvAdaptor.ViewHolder>() {
    sealed class ViewHolder(
        binding: ViewBinding
    ): RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(context: Context, checkSame: Boolean, item: ChatModel, thumbnail: String)

        class MultiMyViewHolder(
            private val binding: ItemRightBinding
        ): ViewHolder(binding) {

            override fun bind(context: Context, checkSame: Boolean, item: ChatModel, thumbnail: String) {
                binding.textMessageContent.text = item.content
                binding.textMessageDate.text = item.timestamp.toStringDate()
            }
        }

        class MultiYourViewHolder(
            private val binding: ItemLeftBinding
        ): ViewHolder(binding) {
//            TODO("glide -> piccasso 로 모듈 변경하기 로딩 이슈있음")
            override fun bind(context: Context, checkSame: Boolean, item: ChatModel, thumbnail: String) {
                Log.d("TAG", "bind: $checkSame")
                with(binding) {
                    if (checkSame.not()) {
                        icUser.visibility = View.VISIBLE
                        Glide.with(context)
                            .load(thumbnail)
                            .placeholder(R.drawable.shape_message_blue)
                            .error(R.drawable.ic_launcher_background)
                            .into(icUser)

                    } else {
                        icUser.visibility = View.GONE
                    }
                    binding.textMessageContent.text = item.content
                    binding.textMessageDate.text = item.timestamp.toStringDate()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        when(viewType) {
            0 -> {
                ViewHolder.MultiYourViewHolder(
                    ItemLeftBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
            else -> {
                ViewHolder.MultiMyViewHolder(
                    ItemRightBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
        }

    override fun getItemViewType(position: Int): Int = viewType(item[position].author)

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // 채팅 윗 챗이 같은 경우 구별
        if (position != 0 && item[position-1].author == item[position].author) {
            holder.bind(context, true, item[position], targetThumbnail)
        } else {
            holder.bind(context, false, item[position], targetThumbnail)
        }
    }

    private fun viewType(author: String): Int =
        if (author == my) 1 else 0
}