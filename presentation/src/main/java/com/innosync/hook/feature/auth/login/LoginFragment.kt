package com.innosync.hook.feature.auth.login

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.innosync.hook.MainActivity
import com.innosync.hook.R
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentLoginBinding
import com.innosync.hook.feature.auth.login.LoginViewModel.Companion.ON_CLICK_JOIN
import com.innosync.hook.feature.auth.login.LoginViewModel.Companion.ON_CLICK_KAKAO
import com.innosync.hook.feature.auth.login.LoginViewModel.Companion.ON_CLICK_LOGIN
import com.innosync.hook.feature.auth.login.LoginViewModel.Companion.ON_FAILED_LOGIN
import com.innosync.hook.feature.auth.login.LoginViewModel.Companion.ON_SUCCESS_LOGIN
import com.innosync.hook.util.shortToast
import com.innosync.hook.util.startActivityWithFinishAll
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.wait


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    override val viewModel: LoginViewModel by viewModels()
    override fun observerViewModel() {
        bindingViewEvent {  event ->
            when(event) {
                ON_CLICK_LOGIN -> {
                    val context = requireContext()
                    with(mBinding) {
                        if (editId.text.isNullOrBlank()) {
                            context.shortToast("아이디가 존재하지 않습니다.")
                            return@bindingViewEvent
                        }
                        if (editPw.text.isNullOrBlank()) {
                            context.shortToast("비밀번호가 존재하지 않습니다.")
                            return@bindingViewEvent
                        }
                        viewModel.basicLogin(
                            userAccount = editId.text.toString().replace(" ", ""),
                            password = editPw.text.toString().replace(" ", "")
                        )
                    }
                }
                ON_CLICK_JOIN -> {

                    findNavController().navigate(
                        R.id.action_loginFragment_to_joinFragment
                    )
                }
                ON_CLICK_KAKAO -> {
                    requireContext().shortToast("미구현된 기능입니다.")
                }
                ON_SUCCESS_LOGIN -> {
                    startActivityWithFinishAll(MainActivity::class.java)
                }
                ON_FAILED_LOGIN -> {
                    requireContext().shortToast("로그인에 실패하였습니다.")
                }
            }
        }


//        val keyHash = Utility.getKeyHash(this)
//        Log.e("Key", "keyHash: ${keyHash}")
//
//        KakaoSdk.init(this, BuildConfig.KAKAO_KEY)
//
//        mBinding.buttonKakaoLogin.setOnClickListener {
//            kakaoLogin(applicationContext) //로그인
//        }
//        mBinding.buttonJoin.setOnClickListener {
////            회원가입 창으로 이동
//        }
//
//        mBinding.buttonLogin.setOnClickListener {
//            val id = mBinding.editId.text.toString().trim()
//            val pw = mBinding.editPw.text.toString().trim()
//
//            saveData(id, pw) //shared preference에 데이터 저장 (자동 로그인용)
//
//            //백엔드 통신 부분
//            val api : Api = Api.create()
//            val data = UserModel (id, pw)
//
//            api.userLogin(data).enqueue(object : Callback<LoginResponse> {
//                override fun onResponse (
//                    call : Call<LoginResponse>,
//                    response: Response<LoginResponse>
//                ) {
//                    Log.d("로그인 통신 성공", response.toString())
//                    Log.d("로그인 통신 성공", response.body().toString())
//
//                    when (reponse.code()) {
//                        200 -> {
//                            saveData(id, pw)
//                        }
//                        405 -> Toast.makeText(this@LoginActivity, "로그인 실패 : 아이디나 비번이 올바르지 않습니다", Toast.LENGTH_SHORT).show()
//                        500 -> Toast.makeText(this@LoginActivity, "로그인 실패 : 서버 오류", Toast.LENGTH_SHORT).show()
//                    }
//                }
//                override fun onFailure (call : Call<LoginResponse>, t : Throwable ) {
//                    //실패
//                    Log.d("로그인 통신 실패", t.message.toString())
//                    Log.d("로그인 통신 실패", "fail")
//                }
//            })
//        }
    }
}

//fun saveData (id : String, pw: String) {
////    val preFID = getSharedPrefeerences("userID", MODE_PRIVATE)
//}
//private fun kakaoLogin(context: Context) {
//    //카카오계정으로 로그인 공통 콜백 구성
//    //카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨
//    val callback : (OAuthToken?, Throwable?) -> Unit = {token, error ->
//        if (error != null) {
//            Log.d(TAG, "카카오계정으로 로그인 실패 :${error}")
//        } else if (token != null) {
//            //최종적으로 카카오로그인 및 유저정보 가져온 결과
//            UserApiClient.instance.me { user, error ->
//                Log.d(TAG, "카카오계정으로 로그인 성공\ntoken: ${token.accessToken}\nme: ${user}")
//                //화면전환
//                val intent = Intent(context, MainActivity::class.java)
//                context.startActivity(intent)
//            }
//        }
//    }
//    //카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오 계정 로그인
//    if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
//        UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
//            if (error != null) {
//                Log.d(TAG, "카카오톡으로 로그인 실패 : ${error}")
//
//                if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
//                    return@loginWithKakaoTalk
//                }
//
//                //카카오톡에 연결된 카카오계정이 없는 경우, 카카오 계정으로 로그인 시도.
//                UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
//            } else if (token != null) {
//                Log.d(TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")
//                //화면전환
//                val intent = Intent(context, MainActivity::class.java)
//                context.startActivity(intent)
//            }
//        } } else {
//        UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
//    }
//}
//private fun kakaoLogout(context: Context) {
//    //로그아웃
//    UserApiClient.instance.logout { error ->
//        if (error != null) {
//            Log.d(TAG, "로그아웃 실패. SDK에서 토큰 삭제됨: ${error}")
//        }
//        else {
//            Log.d(TAG, "로그아웃 성공. SDK에서 토큰 삭제됨")
//        }
//    }
//}

