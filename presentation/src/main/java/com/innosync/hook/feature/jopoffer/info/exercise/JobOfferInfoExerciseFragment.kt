package com.innosync.hook.feature.jopoffer.info.exercise

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentJobOfferInfoExerciseBinding
import com.innosync.hook.util.collectLatestStateFlow
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class JobOfferInfoExerciseFragment: BaseFragment<FragmentJobOfferInfoExerciseBinding, JobOfferInfoExerciseViewModel>()  {

    private val data: JobOfferInfoExerciseFragmentArgs by navArgs()

    override val viewModel: JobOfferInfoExerciseViewModel by viewModels()

    override fun observerViewModel() {
        observeData()
        viewModel.loadInfo(data.id)
        bindingViewEvent { event ->
            when(event) {

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
            }
        }
    }


}