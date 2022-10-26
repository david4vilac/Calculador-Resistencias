package com.david4vilac.calculadorresistencias

import android.content.Intent
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner
import android.widget.Spinner.MODE_DIALOG
import android.widget.Spinner.MODE_DROPDOWN
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.david4vilac.calculadorresistencias.BandWeight.Companion.prefs
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*


@Suppress("DEPRECATION")
class HomeFragment : Fragment() {

    var fiveBands:Boolean? = null
    var unidad:String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadSettings()
        val fab_settings: FloatingActionButton = view.findViewById(R.id.fab_settings)

        val spn1:Spinner = view.findViewById(R.id.colorSpinner)
        val spn2:Spinner = view.findViewById(R.id.colorSpinner2)

        val spn4:Spinner = view.findViewById(R.id.colorSpinner4)
        val spn5:Spinner = view.findViewById(R.id.colorSpinner5)

        val colorList = ColorList(this)

        val colorBand:View = view.findViewById(R.id.firstBand)
        val colorBand2:View = view.findViewById(R.id.firstBand2)
        val colorBand3:View = view.findViewById(R.id.firstBand3)

        val bandMultiplier:View = view.findViewById(R.id.firstMultiplier)
        val bandTolerance:View = view.findViewById(R.id.firstTolerance)

        val textView:TextView = view.findViewById(R.id.textView)


        colorList.loadColorSpinner(spn1, view, colorBand,"primera",textView)
        colorList.loadColorSpinner(spn2, view, colorBand2,"segunda",textView)

        colorList.loadColorMultiplier(spn4, view, bandMultiplier,"multiplicador",textView)
        colorList.loadColorTolerance(spn5, view, bandTolerance,"tolerance",textView)

        if(fiveBands == true) {
            val spn3:Spinner = view.findViewById(R.id.colorSpinner3)
            colorList.loadColorSpinner(spn3, view, colorBand3, "tercera",textView)
            randomBands(spn1,spn2,spn3,spn4, spn5)
            btnRandomFiveBands(spn1,spn2,spn3,spn4, spn5)

        }else{
            randomBands4(spn1,spn2,spn4,spn5)
            btnRandomFourBands(spn1,spn2,spn4,spn5)
        }

        fab_settings.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
        }
    }

    fun btnRandomFourBands(spn1:Spinner,spn2:Spinner,spn4:Spinner, spn5:Spinner){
        val btnRandom:Button? = view?.findViewById(R.id.btnTouch)
        btnRandom?.setOnClickListener{
            randomBands4(spn1,spn2,spn4, spn5)
            setLocale("Default Value")
        }
    }

    fun btnRandomFiveBands(spn1:Spinner,spn2:Spinner,spn3:Spinner,spn4:Spinner, spn5:Spinner){
        val btnRandom:Button? = view?.findViewById(R.id.btnTouch)
        btnRandom?.setOnClickListener{
            randomBands(spn1,spn2,spn3,spn4, spn5)
        }
    }

    fun loadSettings() {
        val sp = PreferenceManager.getDefaultSharedPreferences(requireContext())

        val theme = sp.getString("reply", "")
        val band = sp.getString("band", "")
        val language = sp.getString("language", "")
        val spinnerMode = sp.getString("spinner_mode", "")

        val listener = OnSharedPreferenceChangeListener { _, _ ->
            restartApplication()
        }
        sp.registerOnSharedPreferenceChangeListener(listener)


        setLocale(language!!)
        changeTheme(theme!!, band!!)
        hideBand(band)
        spinnerMode(spinnerMode!!)
    }

    private fun restartApplication() {
        val intent = Intent()
        intent.setClass(requireActivity(), SplashActivity::class.java)
        startActivity(intent)
        requireActivity().finish()

    }

    private fun setLocale(localeName: String) {
        val currentLanguage = requireActivity().intent.getStringExtra("es").toString()
        if (localeName != currentLanguage) {
            var locale = Locale(localeName)
            val res = resources
            val dm = res.displayMetrics
            val conf = res.configuration
            conf.locale = locale
            res.updateConfiguration(conf, dm)
        }
    }

    private fun randomBands(spn1:Spinner, spn2:Spinner, spn3:Spinner, spn4:Spinner, spn5:Spinner) {
        val ran = (1..10).random()
        val ran2 = (1..10).random()
        val ran3 = (1..10).random()
        val ran4 = (1..12).random()
        val ran5 = (1..8).random()

        spn1.setSelection(ran)
        spn2.setSelection(ran2)
        spn3.setSelection(ran3)
        spn4.setSelection(ran4)
        spn5.setSelection(ran5)
    }

    private fun randomBands4(spn1:Spinner, spn2:Spinner, spn4:Spinner, spn5:Spinner) {
        val ran = (1..10).random()
        val ran2 = (1..10).random()
        val ran4 = (1..12).random()
        val ran5 = (1..8).random()

        spn1.setSelection(ran)
        spn2.setSelection(ran2)
        spn4.setSelection(ran4)
        spn5.setSelection(ran5)
    }


    private fun getPesoBand(spn1: Spinner): String {
        val colorObject = spn1.selectedItem as ColorObject
        return colorObject.peso
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

        if (band == "5"){
            prefs.saveName("5","cant_bands")
            containerBand3?.visibility = View.VISIBLE
            colorBand3?.visibility = View.VISIBLE
            fiveBands = true
        }
        if(band == "4"){
            prefs.saveName("", "tercera")
            prefs.saveName("4","cant_bands")
            containerBand3?.visibility = View.GONE
            colorBand3?.visibility = View.GONE
            fiveBands = false
        }
   }

    fun spinnerMode(spinnerMode: String){
        var spn3:Spinner = requireView().findViewById(R.id.colorSpinner3)

/*        val spn2:Spinner = view.findViewById(R.id.colorSpinner2)
        val spn4:Spinner = view.findViewById(R.id.colorSpinner4)
        val spn5:Spinner = view.findViewById(R.id.colorSpinner5)*/

        if(spinnerMode == "dropdown"){
            spn3 = Spinner(context, MODE_DROPDOWN)
        }
        if(spinnerMode == "dialog"){
            spn3 = Spinner(context, MODE_DIALOG)
        }
    }


    fun data(key:String,textView:TextView ){
        textView.text = "$key"
    }

}

