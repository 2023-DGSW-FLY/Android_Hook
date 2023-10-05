package com.innosync.hook.feature.chat

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.innosync.domain.model.RoomModel
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentChatBinding
import com.innosync.hook.feature.chat.ChatViewModel.Companion.ON_CLICK_DUMMY
import com.innosync.hook.util.getYour
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ChatFragment: BaseFragment<FragmentChatBinding, ChatViewModel>() {
    override val viewModel: ChatViewModel by viewModels()


    override fun observerViewModel() {
        viewModel.loadInfo()
        initRv()
        observeData()
        bindingViewEvent { event ->
            when(event) {
                ON_CLICK_DUMMY -> {
                    viewModel.createRoom(
                        "4",
                        "33"
                    )
                }
            }
        }
    }

//    override fun onResume() {
//        super.onResume()
//        viewModel.getUserList(
//            viewModel.myData.value?.id.toString()
//        ) {
//            setRv(it)
//        }
//    }

    private fun setRv(list: List<RoomModel>) {
        val my = viewModel.myData.value?.id.toString()
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
//        val adaptor = ChatRvAdaptor(
//            viewModel.myData.value?.id.toString(),
//            emptyList(),
//            requireContext()
//        ) {}
        with(mBinding) {
//            rvUsers.adapter = adaptor
            rvUsers.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeData() {
        viewModel.myData.observe(this@ChatFragment) {
            viewModel.getUserList(
                it.id.toString()
            ) { data ->
                Log.d(TAG, "observeData: $data")
                setRv(data)
            }
        }
    }

    companion object {
        const val TAG = "LOG"
    }

}