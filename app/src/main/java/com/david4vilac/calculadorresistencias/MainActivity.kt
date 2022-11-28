package com.david4vilac.calculadorresistencias

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.preference.PreferenceManager
import com.david4vilac.calculadorresistencias.BandWeight.Companion.prefs
import java.text.DecimalFormat


class MainActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBarWithNavController(findNavController(R.id.main_fragment))

    }
    override fun onDestroy() {
        super.onDestroy()
        PreferenceManager.getDefaultSharedPreferences(this)
            .unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onResume() {
        super.onResume()
        PreferenceManager.getDefaultSharedPreferences(this)
            .unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        PreferenceManager.getDefaultSharedPreferences(this)
            .unregisterOnSharedPreferenceChangeListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun print(textView:TextView){
        val cant = prefs.getBand()
        val measure_pref = prefs.getMeasure()
        val multiplierText:Float

        val first = prefs.getName()
        val second = prefs.getSecond()
        val third = prefs.getThird()
        val multiplier = prefs.getMultiplier()
        val tolerance = prefs.getTolerance()

        if (cant == "5") {
            if (first == "" || second == "" || third == "" || multiplier == "" || tolerance == "") clearText(textView)
        }
        if (cant == "4") {
            if (first == "" || second == "" || multiplier == "" || tolerance == "") clearText(textView)
        }

        multiplierText = when (multiplier) {
            "1K" -> 1000f
            "10K" -> 10000f
            "100K" -> 100000f
            "1M" -> 1000000f
            "10M" -> 10000000f
            "100M" -> 100000000f
            "1G" -> 1000000000f
            "" -> 1f
            else -> multiplier.toFloat()
        }


        val pruebaAux:Float

        if (first != "" || second != "" || third != ""){
            val prueba = "$first$second$third".toInt() * multiplierText
            pruebaAux = when(measure_pref){
                "nΩ" -> prueba*1000000000
                "µΩ" -> prueba*1000000
                "mΩ" ->prueba*1000
                "Ω" ->prueba/1
                "KΩ" ->prueba/1000f
                "MΩ" ->prueba/1000000f
                "GΩ" ->prueba/1000000000
                else -> prueba
            }
            textView.text = "${getTwoDecimals(pruebaAux.toDouble())} ${prefs.getMeasure()} $tolerance"
        }

    }
    private fun getTwoDecimals(value: Double): String? {
        val df = DecimalFormat("0.00")
        return df.format(value)
    }

    fun clearText(textView:TextView){
        textView.text = "Results"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId){
        R.id.info_menu ->{
            val dialog = CustomAboutMe()
            dialog.show(supportFragmentManager, "Custom About Me")
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController:NavController = findNavController(R.id.main_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onSharedPreferenceChanged(p0: SharedPreferences?, p1: String?) {
        TODO("Not yet implemented")
    }

}