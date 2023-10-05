package com.innosync.hook.feature.joboffermake

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentJobOfferMakeBinding
import com.innosync.hook.feature.joboffermake.JobOfferMakeViewModel.Companion.ON_CLICK_BACK
import com.innosync.hook.feature.joboffermake.JobOfferMakeViewModel.Companion.ON_CLICK_COMPLETE
import dagger.hilt.android.AndroidEntryPoint
import java.util.Collections.checkedList

@AndroidEntryPoint
class JobOfferMakeFragment :BaseFragment<FragmentJobOfferMakeBinding, JobOfferMakeViewModel>() {
    override val viewModel: JobOfferMakeViewModel by viewModels()



    override fun observerViewModel() {
//        TODO("Not yet implemented")
        bindingViewEvent { event ->
            when(event) {
                ON_CLICK_COMPLETE -> {
                    val check = checkedList()
                    if (check.size == 0) {
                        Toast.makeText(requireContext(), "어 직군 눌러줘", Toast.LENGTH_SHORT).show()
                        return@bindingViewEvent
                    }
                    Log.d("TAG", "observerViewModel: $check")
                }
                ON_CLICK_BACK -> {

                }
            }

        }
    }

    fun checkedList(): MutableList<String> {
        val checkList = mutableListOf<String>()
        with(mBinding) {
            if (androidBtn.isChecked) { checkList.add("안드로이드") }
            if (embeddedBtn.isChecked) { checkList.add("임베디드") }
            if (serverBtn.isChecked) { checkList.add("서버") }
            if (gameBtn.isChecked) { checkList.add("게임") }
            if (etcBtn.isChecked) { checkList.add("기타") }
        }
        return checkList
    }
}