package com.divar.utils

import android.util.Log
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun Any?.dLog(plusTag: String = "", tag: String = "MyLog") {
    Log.d("$tag $plusTag", this.toString())
}

val json = Json { ignoreUnknownKeys = true }

inline fun <reified T> T?.toJson(): String? {
    return if (this == null) return null
    else json.encodeToString(this)
}

inline fun <reified T> String?.fromJson(): T? {
    return if (this.isNullOrEmpty()) return null
    else json.decodeFromString(this)
}
