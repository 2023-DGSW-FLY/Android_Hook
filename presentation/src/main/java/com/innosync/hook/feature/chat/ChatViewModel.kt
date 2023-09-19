package com.innosync.hook.feature.chat

import androidx.lifecycle.viewModelScope
import com.innosync.domain.model.RoomData
import com.innosync.domain.usecase.FirebaseGetListUseCase
import com.innosync.domain.usecase.FirebaseInsertUseCase
import com.innosync.hook.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val firebaseGetListUseCase: FirebaseGetListUseCase,
    private val firebaseInsertUseCase: FirebaseInsertUseCase
): BaseViewModel() {

    fun onClickDummy() =
        viewEvent(ON_CLICK_DUMMY)


    fun getUserList(
        userId: String,
        action: (List<RoomData>) -> Unit
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

    companion object {
        const val ON_CLICK_DUMMY = 0
    }


}