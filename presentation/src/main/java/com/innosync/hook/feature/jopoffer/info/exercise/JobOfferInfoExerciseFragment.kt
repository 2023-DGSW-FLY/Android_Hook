package com.innosync.hook.feature.jopoffer.info.exercise

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.innosync.domain.model.RoomModel
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentJobOfferInfoExerciseBinding
import com.innosync.hook.feature.chat.ChatFragment
import com.innosync.hook.feature.jopoffer.info.exercise.JobOfferInfoExerciseViewModel.Companion.ON_CLICK_CHAT
import com.innosync.hook.feature.jopoffer.info.food.JobOfferInfoFoodFragmentDirections
import com.innosync.hook.util.collectLatestStateFlow
import com.innosync.hook.util.shortToast
import com.innosync.hook.util.toImageUrl
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class JobOfferInfoExerciseFragment: BaseFragment<FragmentJobOfferInfoExerciseBinding, JobOfferInfoExerciseViewModel>()  {

    private val data: JobOfferInfoExerciseFragmentArgs by navArgs()

    override val viewModel: JobOfferInfoExerciseViewModel by viewModels()

    override fun observerViewModel() {
        observeData()
        viewModel.loadInfo(data.id)
        viewModel.getMyId()
        bindingViewEvent { event ->
            when(event) {
                ON_CLICK_CHAT -> {
                    openChat()
                }
            }
        }
    }

    private fun openChat() {
        val targetId = viewModel.exerciseInfoState.value!!.userId
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
                return@getRoomInfo viewModel.roomInsert(userId, targetId)
            } else {
                Log.d(ChatFragment.TAG, "openChat: $room")

                val navigate = JobOfferInfoExerciseFragmentDirections.actionJobOfferInfoExerciseFragmentToMessageFragment(
                    room.chatRoomUid, room.roomName, userId, targetId
                )
                findNavController().navigate(navigate)
            }
        }
    }

    private fun observeData() {
        viewModel.exerciseInfoState.observe(this@JobOfferInfoExerciseFragment) {
            with(mBinding) {
                title.text = it.title
                userName.text = it.username
                nickname.text = it.writer
                reallyTechnology.text = it.exercise
                reallyLocation.text = it.dateTime
                Glide.with(requireContext())
                    .load(it.userId.toString().toImageUrl())
                    .into(profile)
            }
        }

        viewModel.moveChat.observe(this@JobOfferInfoExerciseFragment) {
            if (it == true) {
                val targetId = viewModel.exerciseInfoState.value!!.userId
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
                        val navigate = JobOfferInfoExerciseFragmentDirections.actionJobOfferInfoExerciseFragmentToMessageFragment(
                            room.chatRoomUid, room.roomName, userId, targetId
                        )
                        findNavController().navigate(navigate)
                    }
                }
            }
        }
    }


}