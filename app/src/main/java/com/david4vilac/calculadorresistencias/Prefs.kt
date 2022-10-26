package com.david4vilac.calculadorresistencias
import android.content.Context

class Prefs(context: Context) {
    val SHARE_NAME = "Mydtb"


    val storage = context.getSharedPreferences(SHARE_NAME, 0)

    fun saveName(name:String, key:String){
        storage.edit().putString(key, name).apply()
    }

    fun getName():String{
        return storage.getString("primera", "")!!
    }

    fun getSecond():String{
        return storage.getString("segunda", "")!!
    }
    fun getThird():String{
        return storage.getString("tercera", "")!!
    }

    fun getMultiplier():String{
        return storage.getString("multiplicador", "")!!
    }

    fun getTolerance():String{
        return storage.getString("tolerance", "")!!
    }
    fun getBand():String{
        return storage.getString("cant_bands", "")!!
    }

    fun wipe(){
        storage.edit().clear().apply()
    }
}