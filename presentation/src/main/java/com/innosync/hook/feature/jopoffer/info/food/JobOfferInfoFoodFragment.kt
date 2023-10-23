package com.innosync.hook.feature.jopoffer.info.food

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.innosync.domain.model.RoomModel
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentJobOfferInfoExerciseBinding
import com.innosync.hook.databinding.FragmentJobOfferInfoFoodBinding
import com.innosync.hook.databinding.FragmentJobOfferMakeFoodBinding
import com.innosync.hook.feature.chat.ChatFragment.Companion.TAG
import com.innosync.hook.feature.chat.ChatFragmentDirections
import com.innosync.hook.feature.chat.message.MessageFragmentDirections
import com.innosync.hook.feature.jopoffer.info.exercise.JobOfferInfoExerciseFragmentArgs
import com.innosync.hook.feature.jopoffer.info.exercise.JobOfferInfoExerciseViewModel
import com.innosync.hook.feature.jopoffer.info.food.JobOfferInfoFoodViewModel.Companion.ON_CLICK_CHAT
import com.innosync.hook.util.shortToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class JobOfferInfoFoodFragment: BaseFragment<FragmentJobOfferInfoFoodBinding, JobOfferInfoFoodViewModel>()  {

    private val data: JobOfferInfoFoodFragmentArgs by navArgs()

    override val viewModel: JobOfferInfoFoodViewModel by viewModels()
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
        val targetId = viewModel.eatInfoData.value!!.userId
        Log.d(TAG, "openChat: $targetId")
        val userId = viewModel.userID.value!!
        if (targetId == userId) {
            requireContext().shortToast("자기 자신과 채팅할 수 없습니다.")
            return
        }
        viewModel.getRoomInfo(userId) {
            var room: RoomModel? = null
            Log.d(TAG, "openChat: $it")
            for (i in it) {
                Log.d(TAG, "openChat: ${i.users!!.containsKey(targetId)}")
                if (i.users!!.containsKey(targetId)) {
                    room = i
                    break
                }
            }
            if (room == null) {
                // 채팅방 만들기
                return@getRoomInfo viewModel.roomInsert(userId, targetId)
            } else {
                Log.d(TAG, "openChat: $room")
                val navigate = JobOfferInfoFoodFragmentDirections.actionJobOfferInfoFoodFragmentToMessageFragment(
                    room.chatRoomUid, room.roomName, userId, targetId
                )
                findNavController().navigate(navigate)
            }
        }
    }

    private fun observeData() {
        viewModel.eatInfoData.observe(this@JobOfferInfoFoodFragment) {
            with(mBinding) {
//                title.text = it.title
                title.visibility = View.GONE
                userName.text = it.username
                nickname.text = it.writer
                reallyFood.text = it.title
                reallyLocation.text = it.place
            }
        }
        viewModel.moveChat.observe(this@JobOfferInfoFoodFragment) {
            if (it == true) {
                val targetId = viewModel.eatInfoData.value!!.userId
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
                        val navigate = JobOfferInfoFoodFragmentDirections.actionJobOfferInfoFoodFragmentToMessageFragment(
                            room.chatRoomUid, room.roomName, userId, targetId
                        )
                        findNavController().navigate(navigate)
                    }
                }
            }
        }
    }
}