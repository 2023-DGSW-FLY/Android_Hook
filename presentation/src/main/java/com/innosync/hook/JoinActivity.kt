package com.innosync.hook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.innosync.hook.R
import com.innosync.hook.base.BaseActivity
import com.innosync.hook.databinding.ActivityJoinBinding
import com.innosync.hook.databinding.ActivityLoginBinding
import com.kakao.sdk.common.util.Utility

class JoinActivity : BaseActivity<ActivityJoinBinding,JoinViewModel>() {

    override val viewModel: JoinViewModel by viewModels()
    override fun observerViewModel() {
    }

}
