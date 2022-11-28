@file:Suppress("SENSELESS_COMPARISON")

package com.david4vilac.calculadorresistencias

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.Spinner.MODE_DIALOG
import android.widget.Spinner.MODE_DROPDOWN
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.david4vilac.calculadorresistencias.BandWeight.Companion.prefs
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*


@Suppress("DEPRECATION")
var fiveBands:Boolean? = null
var unidad:String? = null
var rb1: RadioButton ?= null
var rb2: RadioButton ?= null
var rg1: RadioGroup ?= null
@Suppress("DEPRECATION")
class HomeFragment : Fragment(){


    private var lang: String = Locale.getDefault().getLanguage()
    private lateinit var contextAux : Context
    private lateinit var activityAux : Activity

    @SuppressLint("DetachAndAttachSameFragment")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val ps: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        contextAux = requireContext()
        val activityAux = requireActivity()

        val listener = OnSharedPreferenceChangeListener { _: SharedPreferences, _: String ->
            hideBand()
            changeTheme()
            //Toast.makeText(context,"${requireContext()}", Toast.LENGTH_SHORT).show()
            restartApplication(activityAux)
        }

        ps.registerOnSharedPreferenceChangeListener(listener)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onPause() {
        contextAux = requireContext()
        activityAux = requireActivity()
        super.onPause()
    }

    override fun onResume() {
        contextAux = requireContext()
        activityAux = requireActivity()
        super.onResume()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var language = prefs.getLanguage()
        Locale.getDefault().getLanguage()
        val fab_settings: FloatingActionButton = view.findViewById(R.id.fab_settings)

        rg1 = view.findViewById(R.id.rg1)
        rb1 = view.findViewById(R.id.rb1)
        rb2 = view.findViewById(R.id.rb2)

        when(language){
            "en" -> rb2?.isChecked = true
            else ->rb1?.isChecked = true
        }


        val spn1:Spinner = view.findViewById(R.id.colorSpinner)
        val spn2:Spinner = view.findViewById(R.id.colorSpinner2)
        val spn3:Spinner = view.findViewById(R.id.colorSpinner3)
        val spn4:Spinner = view.findViewById(R.id.colorSpinner4)
        val spn5:Spinner = view.findViewById(R.id.colorSpinner5)
        val spnMesure: Spinner = view.findViewById(R.id.spMesures)

        val colorList = ColorList()

        val colorBand:View = view.findViewById(R.id.firstBand)
        val colorBand2:View = view.findViewById(R.id.firstBand2)
        val colorBand3:View = view.findViewById(R.id.firstBand3)

        val bandMultiplier:View = view.findViewById(R.id.firstMultiplier)
        val bandTolerance:View = view.findViewById(R.id.firstTolerance)

        val textView:TextView = view.findViewById(R.id.textView)

        colorList.loadColorSpinner(spn1, view, colorBand,"primera",textView)
        colorList.loadColorSpinner(spn2, view, colorBand2,"segunda",textView)
        colorList.loadColorSpinner(spn3, view, colorBand3, "tercera",textView)
        colorList.loadColorMultiplier(spn4, view, bandMultiplier,"multiplicador",textView)
        colorList.loadColorTolerance(spn5, view, bandTolerance,"tolerance",textView)
        loadSpinnerMeasure(spnMesure, textView)
        spnMesure.setSelection(3)
        randomBands(spn1,spn2,spn3,spn4, spn5)
        btnRandomFiveBands(spn1,spn2,spn3,spn4, spn5)

        fab_settings.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
        }
        rb1?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                rb2?.isChecked = false
                lang = "es"
                prefs.saveLaguage(lang)
                updateResource(lang)
                restartApplication(activityAux)
            }
        }
        rb2?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                rb1?.isChecked = false
                lang = "en"
                prefs.saveLaguage(lang)
                updateResource(lang)
                restartApplication(activityAux)
            }
        }
        loadSettings()
    }

    @SuppressLint("ResourceType")
    fun loadSpinnerMeasure(spn:Spinner, textView:TextView) {
        val measure = resources.getStringArray(R.array.unit_mean_entries)
        spn.adapter = ArrayAdapter(requireActivity(), android.R.layout.select_dialog_item , measure)
            //
        spn.onItemSelectedListener = object:
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                prefs.saveMeasure(measure[p2])
                val mainActivity = MainActivity()
                mainActivity.print(textView)
            }
            // HOMEWORK : https://www.digikey.com/es/resources/conversion-calculators/conversion-calculator-resistor-color-code
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
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

        val language = sp.getString("language", "")
        val spinnerMode = sp.getString("spinner_mode", "")

        changeTheme()
        hideBand()
        updateResource(language!!)
        spinnerMode(spinnerMode!!)
    }

    private fun restartApplication(activity: Activity) {
        val intent = Intent()
        if(activity != null){
            intent.setClass(activity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP;
            startActivity(intent)
            activity.finish()
        }


    }

    fun updateResource(idioma:String){
        val recursos = resources
        val displayMetrics = recursos.displayMetrics
        val configuracion = resources.configuration

        configuracion.setLocale(Locale(idioma))
        recursos.updateConfiguration(configuracion, displayMetrics)
        configuracion.locale = Locale(idioma)
        resources.updateConfiguration(configuracion, displayMetrics)

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

    private fun changeTheme(){
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(contextAux)
        val theme = sharedPreferences.getString("reply", "")
        if(theme == "light"){
            prefs.saveTheme("light")
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }else if(theme == "dark"){
            prefs.saveTheme("dark")
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

    private fun hideBand(){
       val band = PreferenceManager.getDefaultSharedPreferences(contextAux).getString("band", "")
       val containerBand3: View? = view?.findViewById(R.id.containerBand3)
       val colorBand3:View? = view?.findViewById(R.id.firstBand3)

       if (band == "5"){
           prefs.saveName("5","cant_bands")
           containerBand3?.visibility = View.VISIBLE
           colorBand3?.visibility = View.VISIBLE
           fiveBands == true
       }else if(band == "4"){
           prefs.saveName("", "tercera")
           prefs.saveName("4","cant_bands")
           containerBand3?.visibility = View.GONE
           colorBand3?.visibility = View.GONE
           fiveBands == false
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

