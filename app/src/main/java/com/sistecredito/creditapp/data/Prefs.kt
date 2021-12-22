package com.sistecredito.creditapp.data

import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context) {

    private val PREFS = "data"
    private val SHARED_CREDIT = "credit"
    private val SHARED_CC = "cc"
    private val SHARED_FEE = "fee"

    private val storage: SharedPreferences = context.getSharedPreferences(PREFS, 0)

    var credit: Int
        get() = storage.getInt(SHARED_CREDIT, 0)
        set(value) = storage.edit().putInt(SHARED_CREDIT, value).apply()

    var cc: Int
        get() = storage.getInt(SHARED_CC, 0)
        set(value) = storage.edit().putInt(SHARED_CC, value).apply()

    var fee: Int
        get() = storage.getInt(SHARED_FEE, 0)
        set(value) = storage.edit().putInt(SHARED_FEE, value).apply()

    fun wipe() {
        storage.edit().clear().apply()
    }
}