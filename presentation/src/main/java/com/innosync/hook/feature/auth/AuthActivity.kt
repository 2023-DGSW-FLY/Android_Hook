package com.innosync.hook.feature.auth

import androidx.activity.viewModels
import com.innosync.hook.base.BaseActivity
import com.innosync.hook.databinding.ActivityAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity: BaseActivity<ActivityAuthBinding, AuthViewModel>() {

    override val viewModel: AuthViewModel by viewModels()
    override fun observerViewModel() {

    }
}