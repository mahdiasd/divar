package com.divar.utils

import android.util.Log

fun Any?.dLog(tag: String = "MyLog", plusTag: String = "") {
    Log.d("$tag $plusTag", this.toString())
}