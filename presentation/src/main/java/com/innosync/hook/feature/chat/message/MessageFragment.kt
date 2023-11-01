package com.innosync.hook.feature.chat.message

import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.innosync.domain.model.ChatModel
import com.innosync.hook.MainActivity
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentMessageBinding
import com.innosync.hook.feature.chat.message.MessageViewModel.Companion.ON_CLICK_SEND
import com.innosync.hook.util.collectLatestStateFlow
import com.innosync.hook.util.toImageUrl
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MessageFragment: BaseFragment<FragmentMessageBinding, MessageViewModel>() {

    private val data: MessageFragmentArgs by navArgs()
    override val viewModel: MessageViewModel by viewModels()

    override fun observerViewModel() {
        setRv(emptyList())
        initRoomName()
        initObserver()
        initRoom()
        val intent = Intent("current_fragment")
        intent.putExtra("fragment_name", "FragmentB")
        requireContext().sendBroadcast(intent)
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
                        viewModel.sendNotification(
                            targetId = data.your,
                            title = "${mBinding.textTopbar.text}",
                            content = editSendMessage.text.toString()
                        )
                        editSendMessage.text?.clear()
                    }
                }
            }
        }
    }

    private fun initRoom() {
        viewModel.addRoomEventListener(
            data.my,
            data.chatUid
        )
    }

    private fun initObserver() {
        collectLatestStateFlow(viewModel.userIdState) {
            mBinding.textTopbar.text = it
        }
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as? MainActivity)?.bottomVisible(false)
        viewModel.insertChat(data.your)
    }

    override fun onPause() {
        super.onPause()
        (requireActivity() as? MainActivity)?.bottomVisible(true)
        viewModel.insertChat("")
    }

    private fun initRoomName() {
        viewModel.loadUserName(data.your)
    }

    private fun setRv(item: List<ChatModel>) {
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