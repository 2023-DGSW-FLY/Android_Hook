package com.innosync.hook.feature.mybox.detailInfo

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.innosync.domain.model.RoomModel
import com.innosync.domain.usecase.firebase.FirebaseGetListUseCase
import com.innosync.domain.usecase.firebase.FirebaseInsertUseCase
import com.innosync.domain.usecase.user.UserGetInfoUseCase
import com.innosync.hook.base.BaseViewModel
import com.innosync.hook.util.launchIO
import com.innosync.hook.util.launchMain
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DetailInfoViewModel @Inject constructor(
    private val firebaseGetListUseCase: FirebaseGetListUseCase,
    private val firebaseInsertUseCase: FirebaseInsertUseCase,
    private val userGetInfoUseCase: UserGetInfoUseCase
): BaseViewModel() {



    private val _visibility = MutableLiveData<Int>(View.GONE)
    val visibility: LiveData<Int> = _visibility

    private val _moveChat = MutableLiveData<Boolean>(false)
    val moveChat: LiveData<Boolean> = _moveChat

    private val _userId = MutableLiveData<String>("")
    val userID: LiveData<String> = _userId

    fun getRoomInfo(userId: String, action: (List<RoomModel>) -> Unit) = launchIO {
        firebaseGetListUseCase.invoke(
            userId = userId
        ) {
            action(it)
        }
    }

    fun roomInsert(userId: String, targetId: String) = launchIO {
        firebaseInsertUseCase.invoke(
            userId = userId,
            targetId = targetId
        ).onSuccess {
            launchMain {
                _moveChat.value = true
            }
        }.onFailures {

        }
    }

    fun getMyId() = launchIO {
        userGetInfoUseCase.invoke().onSuccess {
            launchMain {
                _userId.value = it.id.toString()
            }
        }.onFailures {

        }
    }

    fun moveChat() = launchMain {
        _moveChat.value = false
    }

    fun onClickChat() =
        viewEvent(ON_CLICK_CHAT)

    fun onClickBack() =
        viewEvent(ON_CLICK_BACK)

    companion object {
        const val ON_CLICK_CHAT = 0
        const val ON_CLICK_BACK = 1
    }



}