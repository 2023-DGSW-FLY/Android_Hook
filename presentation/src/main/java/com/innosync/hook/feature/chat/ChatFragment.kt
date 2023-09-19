package com.innosync.hook.feature.chat

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.innosync.domain.model.RoomData
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentChatBinding
import com.innosync.hook.feature.chat.ChatViewModel.Companion.ON_CLICK_DUMMY
import com.innosync.hook.util.getYour
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ChatFragment: BaseFragment<FragmentChatBinding, ChatViewModel>() {
    override val viewModel: ChatViewModel by viewModels()

    private var my = "33"
    override fun observerViewModel() {
        initRv()
        bindingViewEvent { event ->
            when(event) {
                ON_CLICK_DUMMY -> {
                    viewModel.createRoom(
                        my,
                        "33"
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getUserList(
            my
        ) {
            setRv(it)
        }
    }

    private fun setRv(list: List<RoomData>) {
        val adaptor = ChatRvAdaptor(
            my,
            list,
            requireContext()
        ) { data ->
            Log.d(TAG, "setRv: $data")
            val navigate = ChatFragmentDirections.actionChatFragmentToMessageFragment(
                data.chatRoomUid, data.roomName, my, data.getYour(my)
            )
            findNavController().navigate(navigate)
        }
        with(mBinding) {
            rvUsers.adapter = adaptor
            rvUsers.layoutManager = LinearLayoutManager(requireContext())
        }

    }

    private fun initRv() {
        val adaptor = ChatRvAdaptor(
            my,
            emptyList(),
            requireContext()
        ) {}
        with(mBinding) {
            rvUsers.adapter = adaptor
            rvUsers.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    companion object {
        const val TAG = "LOG"
    }

}