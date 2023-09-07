package com.innosync.hook.feature.chat

import androidx.lifecycle.viewModelScope
import com.innosync.data.remote.firebase.response.RoomInfo
import com.innosync.domain.model.RoomData
import com.innosync.domain.usecase.FirebaseGetListUseCase
import com.innosync.hook.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val firebaseGetListUseCase: FirebaseGetListUseCase
): BaseViewModel() {

    fun getUserList(
        userId: String,
        action: (List<RoomData>) -> Unit
    ) = viewModelScope.launch(Dispatchers.IO) {
            firebaseGetListUseCase.invoke(
                userId = userId,
                action = action
            )
        }


}