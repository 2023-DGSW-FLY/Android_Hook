package com.innosync.hook.feature.example

import android.util.Log
import androidx.fragment.app.viewModels
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentExampleBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ExampleFragment @Inject constructor(

): BaseFragment<FragmentExampleBinding, ExampleViewModel>() {

    override val viewModel: ExampleViewModel by viewModels()

    override fun observerViewModel() {
//        TODO("Not yet implemented")
        Log.d("TAG", "observerViewModel: Launch")
    }


}