package com.innosync.hook.feature.chat

import androidx.fragment.app.viewModels
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentChatBinding

class ChatFragment: BaseFragment<FragmentChatBinding, ChatViewModel>() {
    override val viewModel: ChatViewModel by viewModels()

    override fun observerViewModel() {
        bindingViewEvent { event ->
            when(event) {

            }
        }
    }

    fun initRv() {

    }

}