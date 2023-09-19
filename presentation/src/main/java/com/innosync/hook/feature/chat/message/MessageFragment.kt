package com.innosync.hook.feature.chat.message

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.innosync.domain.model.ChatData
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentMessageBinding
import com.innosync.hook.feature.chat.message.MessageViewModel.Companion.ON_CLICK_SEND
import com.innosync.hook.util.toImageUrl
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MessageFragment: BaseFragment<FragmentMessageBinding, MessageViewModel>() {

    private val data: MessageFragmentArgs by navArgs()
    override val viewModel: MessageViewModel by viewModels()

    override fun observerViewModel() {
        setRv(emptyList())
        initRoomName()
        viewModel.addChatEventListener(
            data.chatUid
        ) {
            setRv(it)
        }
        bindingViewEvent { event ->
            when (event) {
                ON_CLICK_SEND -> {
                    with(mBinding) {
                        if (editSendMessage.text.toString().isEmpty()) { return@bindingViewEvent }

                        viewModel.sendMessage(
                            userId = data.my,
                            chatUid = data.chatUid,
                            content = editSendMessage.text.toString()
                        )
                        editSendMessage.text?.clear()
                    }
                }
            }
        }
    }

    private fun initRoomName() {
        mBinding.textTopbar.text = data.roomName
    }

    private fun setRv(item: List<ChatData>) {
        val adaptor = MessageRvAdaptor(
            requireContext(),
            item,
            data.my,
            data.your.toImageUrl()
        )
        with(mBinding) {
            rvMessages.adapter = adaptor
            rvMessages.layoutManager = LinearLayoutManager(requireContext()).apply {
                this.stackFromEnd = true
                this.reverseLayout = false
            }
        }
    }


}