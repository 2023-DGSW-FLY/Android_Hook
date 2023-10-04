package com.innosync.hook.util

import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import com.innosync.domain.model.RoomModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Date
import java.util.Locale

internal fun RoomModel.getYour(my: String): String =
    this.users?.keys?.toList()?.minus(my)?.get(0)!!


internal fun String.toImageUrl(): String =
    "https://image.bugsm.co.kr/artist/images/1000/800491/80049126.jpg"
//    "https://exmaple.com/images/user/$this"

internal fun Long.toStringDate(): String {
    val date = if (android.text.format.DateUtils.isToday(this * 1000L)) {
        SimpleDateFormat("a hh:mm", Locale.getDefault())
    } else {
        SimpleDateFormat("MM-dd", Locale.getDefault())
    }
    return date.format(Date(this * 1000L)).toString().replace("PM", "오후").replace("AM", "오전")
}

internal fun Context.shortToast(text: String) =
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

internal fun Context.longToast(text: String) =
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()

fun <T> AppCompatActivity.collectStateFlow(flow: Flow<T>, collect: suspend (T) -> Unit) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collect(collect)
        }
    }
}

internal fun <T> Fragment.collectLatestStateFlow(flow: Flow<T>, collector: suspend (T) -> Unit) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collectLatest(collector)
        }
    }
}

internal fun ViewModel.launchMain(action: () -> Unit) {
    viewModelScope.launch(Dispatchers.Main) {
        action()
    }
}

internal fun ViewModel.launchIO(action: suspend () -> Unit) {
    viewModelScope.launch(Dispatchers.IO) {
        action()
    }
}
fun AppCompatActivity.startActivityWithFinishAll(activity: Class<*>) {
    val intent = Intent(applicationContext, activity)
    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
    startActivity(intent)
    this.finishAffinity()
}
fun Fragment.startActivityWithFinishAll(activity: Class<*>) {
    val intent = Intent(context!!.applicationContext, activity)
    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
    startActivity(intent)
    this.requireActivity().finishAffinity()
}