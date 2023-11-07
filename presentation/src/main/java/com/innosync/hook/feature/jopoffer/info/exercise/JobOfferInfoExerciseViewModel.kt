package com.innosync.hook.feature.jopoffer.info.exercise

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.innosync.domain.model.ExerciseModel
import com.innosync.domain.model.RoomModel
import com.innosync.domain.usecase.firebase.FirebaseGetListUseCase
import com.innosync.domain.usecase.firebase.FirebaseInsertUseCase
import com.innosync.domain.usecase.jobopening.JobOpeningGetOneExerciseUseCase
import com.innosync.domain.usecase.user.UserGetInfoUseCase
import com.innosync.hook.base.BaseViewModel
import com.innosync.hook.util.launchIO
import com.innosync.hook.util.launchMain
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class JobOfferInfoExerciseViewModel @Inject constructor(
    private val getOneExerciseUseCase: JobOpeningGetOneExerciseUseCase,
    private val firebaseGetListUseCase: FirebaseGetListUseCase,
    private val firebaseInsertUseCase: FirebaseInsertUseCase,
    private val userGetInfoUseCase: UserGetInfoUseCase
): BaseViewModel() {

    private val _exerciseInfoState = MutableLiveData<ExerciseModel>()
    val exerciseInfoState: LiveData<ExerciseModel> = _exerciseInfoState

    private val _visibility = MutableLiveData<Int>(View.GONE)
    val visibility: LiveData<Int> = _visibility

    private val _moveChat = MutableLiveData<Boolean>(false)
    val moveChat: LiveData<Boolean> = _moveChat

    private val _userId = MutableLiveData<String>("")
    val userID: LiveData<String> = _userId

    fun loadInfo(id: Int) {
        launchIO {
            getOneExerciseUseCase.invoke(
                id
            ).onSuccess { result ->
                launchMain {
                    _exerciseInfoState.value = result
                    _visibility.value = View.VISIBLE
                }
            }.onFailures {
                Log.d("TAG", "loadInfo: $it")
            }
        }
    }

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

    fun onClickChat() =
        viewEvent(ON_CLICK_CHAT)

    fun onClickBack() =
        viewEvent(ON_CLICK_BACK)

    companion object {
        const val ON_CLICK_CHAT = 0
        const val ON_CLICK_BACK = 1
    }
}