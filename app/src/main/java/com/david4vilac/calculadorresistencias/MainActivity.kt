package com.david4vilac.calculadorresistencias

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.preference.PreferenceManager
import com.david4vilac.calculadorresistencias.BandWeight.Companion.prefs

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBarWithNavController(findNavController(R.id.main_fragment))

    }

    fun print(textView:TextView){
        val cant = prefs.getBand()
        val multiplierText:Float
        val measure:String


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

        measure = when (multiplier) {
            "1K" -> "KΩ"
            "10K" -> "KΩ"
            "100K" -> "KΩ"
            "1M" -> "MΩ"
            "10M" -> "MΩ"
            "100M" -> "MΩ"
            "1G" -> "GΩ"
            else -> "Ω"

        }

        multiplierText = when (multiplier) {
            "1K" -> 1f
            "10K" -> 10f
            "100K" -> 100f
            "1M" -> 1f
            "10M" -> 10f
            "100M" -> 100f
            "1G" -> 1f
            "" -> 1f
            else -> multiplier.toFloat()
        }




        val prueba = "$first$second$third".toInt() * multiplierText
        if(prueba <= 0f){
            textView.text = "${prueba}$measure$tolerance"
        }else{
            textView.text = "${prueba.toInt()}$measure$tolerance"
        }

        //textView.text = "$first$second$third x $multiplierText $tolerance"
        Log.d("Prueba", "${prueba::class.simpleName}")
    }

    fun defaultUnit(multiplier:Float, result:Float):String{
        val measure:String
        when(multiplier){
            1000f -> ""
            10000f -> ""
            100000f -> ""
            1000000f -> ""
            10000000f -> ""
            100000000f -> ""
            1000000000f -> ""
            1f -> ""
            else -> ""
        }

        return ""
    }

    fun restartApplication(){
        val i = Intent(this, HomeFragment::class.java)
        startActivity(i)
        finish()
    }

    fun clearText(textView:TextView){
        textView.text = "Resultados"
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

}