package com.estudo.carros.utils

import android.content.SharedPreferences
import com.estudo.carros.CarrosApplication

object Prefs {
    val PREF_ID = "carros";

    private fun prefs(): SharedPreferences {
        val context = CarrosApplication.getInstance()?.applicationContext
        return context!!.getSharedPreferences(PREF_ID, 0)
    }

    fun setInt(flag: String, valor: Int) = prefs().edit().putInt(flag, valor).apply()
    fun getInt(flag: String) = prefs().getInt(flag, 0)
    fun setString(flag: String, valor: String) = prefs().edit().putString(flag, valor).apply()
    fun getString(flag:String) = prefs().getString(flag, "")
}