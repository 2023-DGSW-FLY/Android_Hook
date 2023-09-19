package com.innosync.hook


import android.os.Bundle
import androidx.activity.viewModels
import com.innosync.hook.base.BaseActivity
import com.innosync.hook.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val viewModel: MainViewModel by viewModels()
    override fun observerViewModel() {
//        TODO()
        viewModel.getLog()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}