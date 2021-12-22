package com.sistecredito.creditapp

import android.app.Application
import com.sistecredito.creditapp.data.Prefs

class CreditApplication: Application() {

    companion object {
        lateinit var prefs: Prefs
    }

    override fun onCreate() {
        super.onCreate()
        prefs = Prefs(applicationContext)
    }
}