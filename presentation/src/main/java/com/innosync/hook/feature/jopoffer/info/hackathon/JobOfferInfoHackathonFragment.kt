package com.innosync.hook.feature.jopoffer.info.hackathon

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.innosync.hook.MainActivity
import com.innosync.hook.R
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentJobOfferInfoExerciseBinding
import com.innosync.hook.databinding.FragmentJobOfferInfoHackathonBinding
import com.innosync.hook.databinding.FragmentJobOfferMakeFoodBinding
import com.innosync.hook.feature.chat.ChatFragment.Companion.TAG
import com.innosync.hook.feature.jopoffer.info.exercise.JobOfferInfoExerciseFragmentArgs
import com.innosync.hook.feature.jopoffer.info.exercise.JobOfferInfoExerciseViewModel
import com.innosync.hook.feature.jopoffer.info.food.JobOfferInfoFoodViewModel
import com.innosync.hook.feature.jopoffer.info.hackathon.JobOfferInfoHackathonViewModel.Companion.ON_CLICK_BACK
import com.innosync.hook.feature.jopoffer.info.hackathon.JobOfferInfoHackathonViewModel.Companion.ON_CLICK_SUPPORT
import com.innosync.hook.feature.jopoffer.mapper.toTechnology
import com.innosync.hook.util.toImageUrl
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class JobOfferInfoHackathonFragment: BaseFragment<FragmentJobOfferInfoHackathonBinding, JobOfferInfoHackathonViewModel>()  {

    private val data: JobOfferInfoHackathonFragmentArgs by navArgs()

    override val viewModel: JobOfferInfoHackathonViewModel by viewModels()

    override fun observerViewModel() {
        observeData()
        bindingViewEvent { event ->
            when(event) {
                ON_CLICK_SUPPORT -> {
                    val navigate = JobOfferInfoHackathonFragmentDirections.actionJobOfferInfoHackathonFragmentToJobOfferInfoHackathonSupportFragment(
                        data.id
                    )
                    findNavController().navigate(
                        navigate
                    )
                }

                ON_CLICK_BACK ->{
                    Log.d(TAG, "observerViewModel: 뒤로")
                    findNavController().popBackStack()
                }
            }
        }
    }

    private fun observeData() {
        viewModel.hackathonInfoData.observe(this@JobOfferInfoHackathonFragment) {
            with(mBinding) {
//                title.text = it.title
                title.visibility = View.GONE
                userName.text = it.username
                nickname.text = it.writer
                reallyTechnology.text = it.stack.toStacks()
                textLink.text = it.url
                Glide.with(requireContext())
                    .load(it.userId.toString().toImageUrl())
                    .into(profile)
            }
        }
    }

    private fun List<String>.toStacks(): String {
        var result = ""
        for ((cnt, i) in this.withIndex()) {
            result += i
            if (cnt != this.size-1) {
                result += " / "
            }
        }
        return result
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadInfo(data.id)
        val mainActivity = (requireActivity() as MainActivity)
        if (mainActivity.nowSelectItem() != R.id.nav_item_home) {
            Log.d(TAG, "onResume: ddd")
            mainActivity.moveHome()
        }
        Log.d(TAG, "onResume: gg")
    }
}