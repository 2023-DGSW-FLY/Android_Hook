package com.innosync.hook.feature.jopoffer.info.food

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.innosync.domain.model.EatModel
import com.innosync.domain.model.ExerciseModel
import com.innosync.domain.model.RoomModel
import com.innosync.domain.usecase.FirebaseGetListUseCase
import com.innosync.domain.usecase.JobOpeningGetOneEatUseCase
import com.innosync.hook.base.BaseViewModel
import com.innosync.hook.feature.chat.ChatFragmentDirections
import com.innosync.hook.util.getYour
import com.innosync.hook.util.launchIO
import com.innosync.hook.util.launchMain
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class JobOfferInfoFoodViewModel @Inject constructor(
    private val eatUseCase: JobOpeningGetOneEatUseCase,
    private val firebaseGetListUseCase: FirebaseGetListUseCase
): BaseViewModel() {
    private val _eatInfoData = MutableLiveData<EatModel>()
    val eatInfoData: LiveData<EatModel> = _eatInfoData

    private val _visibility = MutableLiveData<Int>(View.GONE)
    val visibility: LiveData<Int> = _visibility

    fun loadInfo(id: Int) = launchIO {
        eatUseCase.invoke(
            id
        ).onSuccess { result ->
            launchMain {
                _eatInfoData.value = result
                _visibility.value = View.VISIBLE
            }
        }.onFailures {
            Log.d("TAG", "loadInfo: $it")
        }
    }
    fun test(userId: String, action: (List<RoomModel>) -> Unit) = launchIO {
        firebaseGetListUseCase.invoke(
            userId = userId
        ) {
            action(it)
        }
    }
}