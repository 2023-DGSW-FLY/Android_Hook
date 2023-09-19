package com.innosync.hook


import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil.setContentView
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.innosync.hook.base.BaseActivity
import com.innosync.hook.databinding.ActivityMainBinding
import com.innosync.hook.feature.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val viewModel: MainViewModel by viewModels()
    override fun observerViewModel() {
//        TODO()
//        supportFragmentManager.beginTransaction().replace(R.id.nav_bottom, HomeFragment()).commit()
        viewModel.getLog()
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment

        val navController = navHostFragment.navController
        mBinding.navBottom
            .setupWithNavController(navController)
//        mBinding.navBottom.setOnItemSelectedListener {
//            when(it.itemId) {
//                R.id.nav_item_home -> {
//                    supportFragmentManager.beginTransaction().replace(R.id.nav_bottom, HomeFragment()).be
//                }
//                R.id.nav_item_calendar -> {}
//                R.id.nav_item_message -> {}
//                R.id.nav_item_myBox -> {}
//            }
//        }
    }

    fun moveHomeToMessage() {
        mBinding.navBottom.selectedItemId = R.id.nav_item_message
    }
    fun moveHomeToCalendar() {
        mBinding.navBottom.selectedItemId = R.id.nav_item_calendar
    }
    fun moveHomeToMyBox() {
        mBinding.navBottom.selectedItemId = R.id.nav_item_myBox
    }
}