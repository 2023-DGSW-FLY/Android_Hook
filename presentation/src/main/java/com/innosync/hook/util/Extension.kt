package com.innosync.hook.util

import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.innosync.di.BuildConfig
import com.innosync.domain.model.RoomModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.io.File
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

internal fun RoomModel.getYour(my: String): String =
    this.users?.keys?.toList()?.minus(my)?.get(0)!!


internal fun String.toImageUrl(): String =
    "${BuildConfig.SERVER}/api/v1/users/image/$this"
//    "https://image.bugsm.co.kr/artist/images/1000/800491/80049126.jpg"
//    "https://exmaple.com/images/user/$this"

internal fun String.toSlice(length: Int): String =
    this.substring(0, if(this.length > length) length else this.length)


internal fun Long.toStringDate(): String {
    val date = if (android.text.format.DateUtils.isToday(this * 1000L)) {
        SimpleDateFormat("a hh:mm", Locale.getDefault())
    } else {
        SimpleDateFormat("MM-dd", Locale.getDefault())
    }
    return date.format(Date(this * 1000L)).toString().replace("PM", "오후").replace("AM", "오전")
}

internal fun LocalDateTime.toStringDate(): String {
    val now = LocalDateTime.now()

    // 현재 날짜와 비교하여 오늘인지 확인
    val isToday = this.toLocalDate() == now.toLocalDate()

    return if (isToday) {
        // 오늘인 경우
        this.format(DateTimeFormatter.ofPattern("HH:mm"))
    } else {
        // 오늘이 아닌 경우
        this.format(DateTimeFormatter.ofPattern("MM/dd"))
    }
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

fun Uri.getRealPathFromURI(context: Context): Uri {
    val cursor = context.contentResolver?.query(this, null, null, null, null)
    cursor?.moveToNext()
    val columnIndex = cursor?.getColumnIndex("_data")
    val picturePath = columnIndex?.let { cursor.getString(it) }
    cursor?.close()
    return Uri.fromFile(File(picturePath ?: ""))
}

fun <T : RecyclerView> T.removeItemDecorations() {
    while (itemDecorationCount > 0) {
        removeItemDecorationAt(0)
    }
}