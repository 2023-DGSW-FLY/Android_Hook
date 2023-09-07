package com.innosync.hook.feature.chat

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.innosync.data.remote.firebase.response.RoomInfo
import com.innosync.domain.model.RoomData
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentChatBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ChatFragment: BaseFragment<FragmentChatBinding, ChatViewModel>() {
    override val viewModel: ChatViewModel by viewModels()

    private var my = "11"
    override fun observerViewModel() {
        initRv()
        viewModel.getUserList(
            my
        ) {
            setRv(it)
        }
        bindingViewEvent { event ->
            when(event) {

            }
        }
    }

    private fun setRv(list: List<RoomData>) {
        val adaptor = ChatRvAdaptor(
            my,
            list,
            requireContext()
        ) { data ->
            Log.d(TAG, "setRv: $data")
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