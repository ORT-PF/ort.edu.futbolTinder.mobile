package ort.edu.ar.futboltinder.activities.home.fragments

import android.widget.TextView
import java.time.Duration
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

private val formatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH)

fun TextView.getTime(): LocalTime {
    return LocalTime.parse(text, formatter)
}

fun TextView.setTime(time: LocalTime) {
    text = time.toTimeText()
}

fun LocalTime.toTimeText(): String {
    return format(formatter)
}

