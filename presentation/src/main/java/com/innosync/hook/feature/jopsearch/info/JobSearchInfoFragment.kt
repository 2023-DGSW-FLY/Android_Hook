package com.innosync.hook.feature.jopsearch.info

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.innosync.domain.model.RoomModel
import com.innosync.hook.MainActivity
import com.innosync.hook.R
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentJobSearchBinding
import com.innosync.hook.databinding.FragmentJobSearchInfoBinding
import com.innosync.hook.feature.chat.ChatFragment
import com.innosync.hook.feature.chat.ChatFragment.Companion.TAG
import com.innosync.hook.feature.jopoffer.info.food.JobOfferInfoFoodFragmentDirections
import com.innosync.hook.feature.jopsearch.info.JobSearchInfoViewModel.Companion.ON_CLICK_BACK
import com.innosync.hook.feature.jopsearch.info.JobSearchInfoViewModel.Companion.ON_CLICK_CHAT
import com.innosync.hook.util.collectLatestStateFlow
import com.innosync.hook.util.launchIO
import com.innosync.hook.util.launchMain
import com.innosync.hook.util.shortToast
import com.innosync.hook.util.toImageUrl
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class JobSearchInfoFragment: BaseFragment<FragmentJobSearchInfoBinding, JobSearchInfoViewModel>() {


    private val args: JobSearchInfoFragmentArgs by navArgs()
    override val viewModel: JobSearchInfoViewModel by viewModels()

    override fun onStart() {
        initObserver()
        super.onStart()
    }

    override fun observerViewModel() {
        viewModel.load(args.id)
        viewModel.getMyId()
//        initObserver()

        bindingViewEvent {
            when(it) {
                ON_CLICK_CHAT -> {
                    openChat()
                }

                ON_CLICK_BACK ->{
                    Log.d(TAG, "observerViewModel: ")
                    findNavController().popBackStack()
                }
            }
        }
    }

    private fun initObserver() {
        collectLatestStateFlow(viewModel.jobSearchState) {
            if (it == null) {  return@collectLatestStateFlow }

            with(mBinding) {
                nickname.text = it.writer
                reallyTechnology.text = it.stack
                textPortfolio.text = it.url
                textContent.text = it.content
                Glide.with(requireContext())
                    .load(it.userId.toImageUrl())
                    .into(profile)
            }
        }
        viewModel.moveChat.observe(this@JobSearchInfoFragment) {
            if (it == true) {
                val targetId = viewModel.jobSearchState.value!!.userId
                val userId = viewModel.userID.value!!
                viewModel.getRoomInfo(userId) {
                    var room: RoomModel? = null
                    for (i in it) {
                        if (i.users!!.containsKey(targetId)) {
                            room = i
                            break
                        }
                    }
                    if (room != null) {
                        // 채팅방 만들기
                        val navigate = JobSearchInfoFragmentDirections.actionJobSearchInfoFragmentToMessageFragment(
                            room.chatRoomUid, room.roomName, userId, targetId
                        )
                        findNavController().navigate(navigate)
                    }
                }
            }
        }
    }

    private fun openChat() {
        val targetId = viewModel.jobSearchState.value!!.userId
        Log.d(ChatFragment.TAG, "openChat: $targetId")
        val userId = viewModel.userID.value!!
        if (targetId == userId) {
            requireContext().shortToast("자기 자신과 채팅할 수 없습니다.")
            return
        }
        viewModel.getRoomInfo(userId) {
            var room: RoomModel? = null
            Log.d(ChatFragment.TAG, "openChat: $it")
            for (i in it) {
                Log.d(ChatFragment.TAG, "openChat: ${i.users!!.containsKey(targetId)}")
                if (i.users!!.containsKey(targetId)) {
                    room = i
                    break
                }
            }
            if (room == null) {
                // 채팅방 만들기
                viewModel.roomInsert(userId, targetId)
                return@getRoomInfo
            } else {
                Log.d(ChatFragment.TAG, "openChat: $room")
                val navigate = JobSearchInfoFragmentDirections.actionJobSearchInfoFragmentToMessageFragment(
                    room.chatRoomUid, room.roomName, userId, targetId
                )
                findNavController().navigate(navigate)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val mainActivity = (requireActivity() as MainActivity)
        if (mainActivity.nowSelectItem() != R.id.nav_item_home) {
            Log.d(TAG, "onResume: ddd")
            mainActivity.moveHome()
        }
    }




}