package com.divar.utils

import android.util.Log

fun Any?.dLog(plusTag: String = "", tag: String = "MyLog") {
    Log.d("$tag $plusTag", this.toString())
}

