package com.divar.network.preferences

import android.content.SharedPreferences
import com.divar.secure_shared_pref.SharedPrefConstant
import javax.inject.Inject

class TokenPreferences @Inject constructor(private val sharedPreferences: SharedPreferences) {
    fun readToken(): String {
        return sharedPreferences.getString(SharedPrefConstant.TOKEN, "") ?: ""
    }
}