package com.innosync.hook.feature.auth.join

import androidx.fragment.app.viewModels
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentJoinBinding
import com.innosync.hook.feature.auth.join.JoinViewModel.Companion.ON_CLICK_JOIN
import com.innosync.hook.util.shortToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JoinFragment : BaseFragment<FragmentJoinBinding, JoinViewModel>() {

    override val viewModel: JoinViewModel by viewModels()
    override fun observerViewModel() {
        bindingViewEvent {  event ->
            when(event) {
                ON_CLICK_JOIN -> {
                    with(mBinding) {
                        val context = requireContext()
                        if (editId.text.isNullOrBlank()) {
                            context.shortToast("아이디가 존재하지 않습니다.")
                            return@bindingViewEvent
                        }
                        if (editPassword.text.isNullOrBlank()) {
                            context.shortToast("비밀번호가 존재하지 않습니다.")
                            return@bindingViewEvent
                        }
                        if (editName.text.isNullOrBlank()) {
                            context.shortToast("이름이 존재하지 않습니다.")
                            return@bindingViewEvent
                        }
                        if (editEmail.text.isNullOrBlank()) {
                            context.shortToast("이메일이 존재하지 않습니다.")
                            return@bindingViewEvent
                        }
                        if (editGithub.text.isNullOrBlank()) {
                            context.shortToast("깃허브 주소가 존재하지 않습니다.")
                            return@bindingViewEvent
                        }
                        if (editGithub.text.contains("https://github.com/").not()) {
                            context.shortToast("깃허브 주소가 올바르지 않습니다.")
                            return@bindingViewEvent
                        }
                        if (editPortfolio.text.isNullOrBlank()) {
                            context.shortToast("포트폴리오 주소가 존재하지 않습니다.")
                            return@bindingViewEvent
                        }
                        viewModel.join(
                            userAccount = editId.text.toString().replace(" ", ""),
                            password = editPassword.text.toString().replace(" ", ""),
                            userName = editName.text.toString().replace(" ", ""),
                            email = editEmail.text.toString().replace(" ", ""),
                            userInfo = "".replace(" ", ""),
                            githubURL = editGithub.text.toString().replace(" ", ""),
                            portfolioURL = editPortfolio.text.toString().replace(" ", "")
                        )

                    }
                }
            }
        }
    }

}
