package com.innosync.hook.feature.jopsearch

import android.view.View
import android.widget.CheckBox
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.innosync.hook.base.BaseViewModel
import com.innosync.hook.feature.jopoffer.JopOfferViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class JopSearchViewModel @Inject constructor(

): BaseViewModel() {
    private val _selectLivedata = MutableLiveData<MutableMap<String, Boolean>>()
    val selectLivedata: LiveData<MutableMap<String, Boolean>> = _selectLivedata

    fun onClick(view: View) {

    }

    fun onClickAndroid() {
        addSelectData("android")
    }




    private fun addSelectData(id: String) {
        if (_selectLivedata.value!!.containsKey(id)) {
            _selectLivedata.value!![id] = _selectLivedata.value!![id]?.not()!!
        } else {
            _selectLivedata.value!![id] = true
        }
    }

    companion object {
        const val
    }

}