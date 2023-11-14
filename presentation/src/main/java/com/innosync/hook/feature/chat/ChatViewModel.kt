package com.innosync.hook.feature.chat

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.innosync.domain.model.RoomModel
import com.innosync.domain.model.UserModel
import com.innosync.domain.usecase.firebase.FirebaseGetListUseCase
import com.innosync.domain.usecase.firebase.FirebaseInsertUseCase
import com.innosync.domain.usecase.chat.ChatGetTheseInfoUseCase
import com.innosync.domain.usecase.user.UserGetInfoUseCase
import com.innosync.hook.base.BaseViewModel
import com.innosync.hook.util.launchIO
import com.innosync.hook.util.launchMain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val firebaseGetListUseCase: FirebaseGetListUseCase,
    private val firebaseInsertUseCase: FirebaseInsertUseCase,
    private val userGetInfoUseCase: UserGetInfoUseCase,
    private val chatGetTheseInfoUseCase: ChatGetTheseInfoUseCase
): BaseViewModel() {

    private val _myData = MutableLiveData<UserModel>()
    val myData: LiveData<UserModel> = _myData

    private val _usersData = MutableStateFlow<Map<String, String>>(emptyMap())
    val usersData = _usersData.asStateFlow()

    fun onClickDummy() =
        viewEvent(ON_CLICK_DUMMY)

    fun loadInfo() = launchIO {
        userGetInfoUseCase.invoke()
            .onSuccess {
                launchMain {
                    _myData.value = it
                }
            }.onFailures {
                Log.d("TAG", "loadInfo: $it")
            }
    }

    fun getUserList(
        userId: String,
        action: (List<RoomModel>) -> Unit
    ) = viewModelScope.launch(Dispatchers.IO) {
            firebaseGetListUseCase.invoke(
                userId = userId,
                action = action
            )
        }

    fun createRoom(
        userId: String,
        targetId: String
    ) = viewModelScope.launch(Dispatchers.IO) {
        firebaseInsertUseCase.invoke(
            userId = userId,
            targetId = targetId
        )
    }

    fun getThese(
        users: List<String>
    ) = launchIO {
        chatGetTheseInfoUseCase.invoke(
            users
        ).onSuccess {
            _usersData.value = it
        }.onFailures {

        }
    }

    companion object {
        const val ON_CLICK_DUMMY = 0
    }


}