package com.example.qrather.ui.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun Calendar.toFormatedDisplay () : String{
    val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy hh:mm:a", Locale.US)
    return simpleDateFormat.format(this.time)
}