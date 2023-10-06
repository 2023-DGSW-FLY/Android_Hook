package com.innosync.hook.feature.jopoffer.info.hackathon

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentJobOfferInfoExerciseBinding
import com.innosync.hook.databinding.FragmentJobOfferInfoHackathonBinding
import com.innosync.hook.databinding.FragmentJobOfferMakeFoodBinding
import com.innosync.hook.feature.jopoffer.info.exercise.JobOfferInfoExerciseFragmentArgs
import com.innosync.hook.feature.jopoffer.info.exercise.JobOfferInfoExerciseViewModel
import com.innosync.hook.feature.jopoffer.info.food.JobOfferInfoFoodViewModel
import com.innosync.hook.feature.jopoffer.info.hackathon.JobOfferInfoHackathonViewModel.Companion.ON_CLICK_SUPPORT
import com.innosync.hook.feature.jopoffer.mapper.toTechnology
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class JobOfferInfoHackathonFragment: BaseFragment<FragmentJobOfferInfoHackathonBinding, JobOfferInfoHackathonViewModel>()  {

    private val data: JobOfferInfoHackathonFragmentArgs by navArgs()

    override val viewModel: JobOfferInfoHackathonViewModel by viewModels()

    override fun observerViewModel() {
        observeData()
        viewModel.loadInfo(data.id)
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
            }
        }
    }

    private fun List<String>.toStacks(): String {
        var result = ""
        for (i in this) {
            result += i
        }
        return result
    }
}