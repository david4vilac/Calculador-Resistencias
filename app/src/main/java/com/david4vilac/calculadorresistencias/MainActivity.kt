package com.david4vilac.calculadorresistencias

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        val first = prefs.getName()
        val second = prefs.getSecond()
        val third = prefs.getThird()
        val multiplier = prefs.getMultiplier()
        val tolerance = prefs.getTolerance()
        textView.text = "$first$second$third x $multiplier $tolerance"
        if (cant == "5") {
            if (first == "" || second == "" || third == "" || multiplier == "" || tolerance == "") clearText(
                textView
            )
        }
        if (cant == "4") {
            if (first == "" || second == "" || multiplier == "" || tolerance == "") clearText(
                textView
            )
        }




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