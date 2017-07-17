package com.siegmund.moviesapp.ui.util

import java.text.SimpleDateFormat
import java.util.Locale.GERMAN

fun String.asGermanDate(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd", GERMAN)
    val date = sdf.parse(this)
    sdf.applyPattern("dd.MM.yyyy")
    return sdf.format(date)
}