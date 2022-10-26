package com.david4vilac.calculadorresistencias

import android.app.Application

class BandWeight: Application() {

        companion object{
            lateinit var prefs: Prefs
        }

        override fun onCreate() {
            super.onCreate()
            prefs = Prefs(applicationContext)
        }
    }