package com.innosync.hook.feature.jopoffer.info.food

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.innosync.domain.model.RoomModel
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentJobOfferInfoExerciseBinding
import com.innosync.hook.databinding.FragmentJobOfferInfoFoodBinding
import com.innosync.hook.databinding.FragmentJobOfferMakeFoodBinding
import com.innosync.hook.feature.chat.ChatFragmentDirections
import com.innosync.hook.feature.jopoffer.info.exercise.JobOfferInfoExerciseFragmentArgs
import com.innosync.hook.feature.jopoffer.info.exercise.JobOfferInfoExerciseViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class JobOfferInfoFoodFragment: BaseFragment<FragmentJobOfferInfoFoodBinding, JobOfferInfoFoodViewModel>()  {

    private val data: JobOfferInfoFoodFragmentArgs by navArgs()

    override val viewModel: JobOfferInfoFoodViewModel by viewModels()

    override fun observerViewModel() {
        observeData()
        viewModel.loadInfo(data.id)
        bindingViewEvent { event ->
            when(event) {

            }
        }
    }
    private fun initAdaptor() {
        val targetId = ""
        val userId = ""
//        viewModel.test("") {
//            val room: RoomModel
//            for (i in it) {
//                if (i.users!!.containsKey(targetId)) {
//                    room = i
//                    break
//                }
//            }
//            if (room == null) {
//                return@test
//            }
//            val navigate = ChatFragmentDirections.actionNavItemMessageToMessageFragment(
//                room.chatRoomUid, room.roomName, userId, targetId
//            )
//            findNavController().navigate(navigate)
//        }
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
    }
}