package com.innosync.hook.util

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.widget.Toast
import com.innosync.domain.model.RoomModel
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