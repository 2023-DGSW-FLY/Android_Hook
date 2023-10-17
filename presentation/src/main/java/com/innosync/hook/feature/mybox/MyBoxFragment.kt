package com.innosync.hook.feature.mybox

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentMyBoxBinding
import com.innosync.hook.util.ItemSpacingDecoration

class MyBoxFragment : BaseFragment<FragmentMyBoxBinding, MyBoxViewModel>() {

    override val viewModel: MyBoxViewModel by viewModels()
    override fun observerViewModel() {


    }


    private fun initLiveDataObserver() {
        viewModel.rvData.observe(viewLifecycleOwner) {

        }
    }

}


