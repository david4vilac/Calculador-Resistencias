package com.david4vilac.calculadorresistencias

import android.content.Intent
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.google.android.material.floatingactionbutton.FloatingActionButton


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val fab_settings: FloatingActionButton = view.findViewById(R.id.fab_settings)

        val spn1: Spinner = view.findViewById(R.id.colorSpinner)
        val spn2: Spinner = view.findViewById(R.id.colorSpinner2)
        val spn3: Spinner = view.findViewById(R.id.colorSpinner3)
        val spn4: Spinner = view.findViewById(R.id.colorSpinner4)
        val spn5: Spinner = view.findViewById(R.id.colorSpinner5)

        val colorBand: View = view.findViewById(R.id.firstBand)
        val colorBand2: View = view.findViewById(R.id.firstBand2)
        val colorBand3: View = view.findViewById(R.id.firstBand3)

        val bandMultiplier:View = view.findViewById(R.id.firstMultiplier)
        val bandTolerance:View = view.findViewById(R.id.firstTolerance)




        val colorList = ColorList(this)
        colorList.loadColorSpinner(spn1, view, colorBand)
        colorList.loadColorSpinner(spn2, view, colorBand2)
        colorList.loadColorSpinner(spn3, view, colorBand3)
        colorList.loadColorMultiplier(spn4, view, bandMultiplier )
        colorList.loadColorTolerance(spn5, view, bandTolerance )

        loadSettings()

        fab_settings.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
        }

    }
    fun loadSettings() {
        val sp = PreferenceManager.getDefaultSharedPreferences(requireContext())

        val theme = sp.getString("reply", "")
        val band = sp.getString("band", "")


        val listener = OnSharedPreferenceChangeListener { _, _ ->
            restartApplication()
        }
        sp.registerOnSharedPreferenceChangeListener(listener)


        changeTheme(theme!!, band!!)
        hideBand(band)
    }

    private fun restartApplication() {
        Toast.makeText(context, "CAMBIADO", Toast.LENGTH_SHORT).show()

        /*val intent = Intent(context, SettingsFragment::class.java)
        finish()
        startActivity(intent)*/


    }

    private fun finish() {
        throw RuntimeException("Stub!")
    }

    fun changeTheme(theme:String, band: String) {
        hideBand(band)
        if(theme == "light"){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

   fun hideBand(band: String){
        val containerBand3: View? = view?.findViewById(R.id.containerBand3)
        val colorBand3:View? = view?.findViewById(R.id.firstBand3)

        if (band == "4"){
            containerBand3?.visibility = View.GONE
            colorBand3?.visibility = View.GONE
        }
        if(band == "5"){
            containerBand3?.visibility = View.VISIBLE
            colorBand3?.visibility = View.VISIBLE
        }
    }
    /*fun restartApplication(){
        val i = Intent(this, SettingsFragment::class.java)
        startActivity(i)
        finish()
    }*/
}