package com.innosync.hook.feature.mybox.detailInfo

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.innosync.domain.model.RoomModel
import com.innosync.hook.MainActivity
import com.innosync.hook.R
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentDetailInfoBinding
import com.innosync.hook.feature.chat.ChatFragment
import com.innosync.hook.feature.jopoffer.info.food.JobOfferInfoFoodFragmentDirections
import com.innosync.hook.feature.jopoffer.info.food.JobOfferInfoFoodViewModel
import com.innosync.hook.feature.loading.LoadingDialog
import com.innosync.hook.util.shortToast
import com.innosync.hook.util.toImageUrl
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailInfoFragment : BaseFragment<FragmentDetailInfoBinding, DetailInfoViewModel>() {


    override val viewModel: DetailInfoViewModel by viewModels()
    private val data: DetailInfoFragmentArgs by navArgs()
    private var loadingDialog: LoadingDialog? = null

    override fun observerViewModel() {
        mBinding.nameEditText.text = data.applicantName
        mBinding.memberNumberEditText.text = data.studentId
        mBinding.memberCallEditText.text = data.contact
        mBinding.selfIntroductionEditText.text = data.introduction
        mBinding.portfolioEditText.setText(data.portfolioLink)

        observeData()
        viewModel.getMyId()
        bindingViewEvent { event ->
            when(event) {
                JobOfferInfoFoodViewModel.ON_CLICK_CHAT -> {
                    openChat()
                }

                JobOfferInfoFoodViewModel.ON_CLICK_BACK ->{
                    Log.d(ChatFragment.TAG, "observerViewModel: 뒤로")
                    findNavController().popBackStack()
                }
            }
        }
    }
    private fun openChat() {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog(requireContext())
        }
        loadingDialog!!.show()
        val targetId = data.userId
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
                val navigate = DetailInfoFragmentDirections.actionDetailInfoFragmentToMessageFragment(
                    room.chatRoomUid, room.roomName, userId, targetId
                )
                dismissScreen()
                findNavController().navigate(navigate)
            }
        }
    }

    private fun observeData() {
        viewModel.moveChat.observe(this@DetailInfoFragment) {
            if (it == true) {
                viewModel.moveChat()
                val targetId = data.userId
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
                        val navigate = DetailInfoFragmentDirections.actionDetailInfoFragmentToMessageFragment(
                            room.chatRoomUid, room.roomName, userId, targetId
                        )
                        dismissScreen()
                        findNavController().navigate(navigate)
                    }
                }
            }
        }

    }

    private fun dismissScreen() {
        if (loadingDialog!=null && loadingDialog!!.isShowing){
            loadingDialog!!.dismiss()
        }
    }

    override fun onResume() {
        super.onResume()
        val mainActivity = (requireActivity() as MainActivity)
        if (mainActivity.nowSelectItem() != R.id.nav_item_myBox) {
            Log.d(ChatFragment.TAG, "onResume: ddd")
            mainActivity.moveMyBox()
        }
    }

    private fun observeState() {
//        collectLatestStateFlow(viewModel.rvData) {
//            val adapter = ApplicantsAdapter(it) {
////                findNavController().navigate()
//                //네비게이션 값 넘기기
//
//            }
//            mBinding.applicantsRecyclerView.adapter = adapter
//        }
    }
}