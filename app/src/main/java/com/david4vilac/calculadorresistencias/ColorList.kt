package com.david4vilac.calculadorresistencias

import android.annotation.SuppressLint
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import com.david4vilac.calculadorresistencias.BandWeight.Companion.prefs

class ColorList(context: HomeFragment) {


    lateinit var selectedColor: ColorObject

    fun bandColors(view: View): List<ColorObject>
    {
        val band = view.resources.getStringArray(R.array.bandName)
        val bandColor = view.resources.getIntArray(R.array.bandColorNum)
        return listOf(
            ColorObject(band[0], bandColor[0], "FFFFFFFF",""),
            ColorObject(band[1], bandColor[1], "FFFFFFFF","0"),
            ColorObject(band[2], bandColor[2], "FFFFFFFF", "1"),
            ColorObject(band[3], bandColor[3], "FFFFFFFF", "2"),
            ColorObject(band[4], bandColor[4], "FFFFFFFF", "3"),
            ColorObject(band[5], bandColor[5], "FFFFFFFF", "4"),
            ColorObject(band[6], bandColor[6], "FFFFFFFF", "5"),
            ColorObject(band[7], bandColor[7], "FFFFFFFF", "6"),
            ColorObject(band[8], bandColor[8], "FFFFFFFF", "7"),
            ColorObject(band[9], bandColor[9], "FFFFFFFF", "8"),
            ColorObject(band[10], bandColor[10], "000000", "9"),
        )
    }

    fun multiplierColors(view: View): List<ColorObject> {
        val band = view.resources.getStringArray(R.array.bandName)
        val bandColor = view.resources.getIntArray(R.array.bandColorNum)
        return listOf(
            ColorObject(band[0], bandColor[0], "FFFFFFFF",""),
            ColorObject(band[1], bandColor[1], "FFFFFFFF","1"),
            ColorObject(band[2], bandColor[2], "FFFFFFFF", "10"),
            ColorObject(band[3], bandColor[3], "FFFFFFFF", "100"),
            ColorObject(band[4], bandColor[4], "FFFFFFFF", "1K"),
            ColorObject(band[5], bandColor[5], "FFFFFFFF", "10K"),
            ColorObject(band[6], bandColor[6], "FFFFFFFF", "100K"),
            ColorObject(band[7], bandColor[7], "FFFFFFFF", "1M"),
            ColorObject(band[8], bandColor[8], "FFFFFFFF", "10M"),
            ColorObject(band[9], bandColor[9], "FFFFFFFF", "100M"),
            ColorObject(band[10], bandColor[10], "000000", "1G"),
            ColorObject(band[11], bandColor[11], "000000", "0.1"),
            ColorObject(band[12], bandColor[12], "000000", "0.01"),
        )
    }

    fun toleranceColors(view: View): List<ColorObject>{
        val band = view.resources.getStringArray(R.array.tolerance)
        val bandColor = view.resources.getIntArray(R.array.toleranceColorNum)
        return listOf(
            ColorObject(band[0], bandColor[0], "FFFFFFFF",""),
            ColorObject(band[1], bandColor[1], "FFFFFFFF","(±)1%"),
            ColorObject(band[2], bandColor[2], "FFFFFFFF", "(±)2%"),
            ColorObject(band[3], bandColor[3], "FFFFFFFF", "(±)0.5%"),
            ColorObject(band[4], bandColor[4], "FFFFFFFF", "(±)0.25%"),
            ColorObject(band[5], bandColor[5], "FFFFFFFF", "(±)0.1%"  ),
            ColorObject(band[6], bandColor[6], "FFFFFFFF", "(±)0.05%"),
            ColorObject(band[7], bandColor[7], "FFFFFFFF", "(±)5%"),
            ColorObject(band[8], bandColor[8], "FFFFFFFF", "(±)10%"),
        )
    }


    fun loadColorSpinner(spn:Spinner, view:View ,viewColorBand:View, key:String, textView:TextView ){
        val colorList = bandColors(view)
        spn.apply {
            adapter = ColorSpinnerAdapter(context, colorList)
            onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                @SuppressLint("ResourceAsColor", "CommitPrefEdits")
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    selectedColor = colorList[p2]
                    when (p2) {
                        0 -> viewColorBand.setBackgroundColor(resources.getColor(R.color.white))
                        1 -> viewColorBand.setBackgroundColor(resources.getColor(R.color.black))
                        2 -> viewColorBand.setBackgroundColor(resources.getColor(R.color.brown))
                        3 -> viewColorBand.setBackgroundColor(resources.getColor(R.color.red))
                        4 -> viewColorBand.setBackgroundColor(resources.getColor(R.color.orange))
                        5 -> viewColorBand.setBackgroundColor(resources.getColor(R.color.yellow))
                        6 -> viewColorBand.setBackgroundColor(resources.getColor(R.color.green))
                        7 -> viewColorBand.setBackgroundColor(resources.getColor(R.color.blue))
                        8 -> viewColorBand.setBackgroundColor(resources.getColor(R.color.purple))
                        9 -> viewColorBand.setBackgroundColor(resources.getColor(R.color.gray))
                        10 -> viewColorBand.setBackgroundColor(resources.getColor(R.color.white))
                    }
                    prefs.saveName(colorList[p2].peso, key)
                    val mainActivity = MainActivity()
                    mainActivity.print(textView)
                    if(p2 == 0) mainActivity.clearText(textView)

                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")

                }
            }
        }
    }

    fun loadColorMultiplier( spnMultiplier:Spinner, view:View, viewColorBand:View, key:String, textView:TextView) {
        val multiplierColors = multiplierColors(view)
        spnMultiplier.apply{
            adapter = ColorSpinnerAdapter(context, multiplierColors)
            onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                @SuppressLint("ResourceAsColor")
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    selectedColor = multiplierColors[p2]
                    when (p2) {
                        0 -> viewColorBand.setBackgroundColor(resources.getColor(R.color.white))
                        1 -> viewColorBand.setBackgroundColor(resources.getColor(R.color.black))
                        2 -> viewColorBand.setBackgroundColor(resources.getColor(R.color.brown))
                        3 -> viewColorBand.setBackgroundColor(resources.getColor(R.color.red))
                        4 -> viewColorBand.setBackgroundColor(resources.getColor(R.color.orange))
                        5 -> viewColorBand.setBackgroundColor(resources.getColor(R.color.yellow))
                        6 -> viewColorBand.setBackgroundColor(resources.getColor(R.color.green))
                        7 -> viewColorBand.setBackgroundColor(resources.getColor(R.color.blue))
                        8 -> viewColorBand.setBackgroundColor(resources.getColor(R.color.purple))
                        9 -> viewColorBand.setBackgroundColor(resources.getColor(R.color.gray))
                        10 -> viewColorBand.setBackgroundColor(resources.getColor(R.color.white))
                        11 -> viewColorBand.setBackgroundColor(resources.getColor(R.color.golden))
                        12 -> viewColorBand.setBackgroundColor(resources.getColor(R.color.silver))
                        //colorSpinner.(Color.parseColor("#000000"))
                    }
                    prefs.saveName(multiplierColors[p2].peso, key)
                    val mainActivity = MainActivity()
                    mainActivity.print(textView)
                    if(p2 == 0) mainActivity.clearText(textView)
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }
    }

    fun loadColorTolerance( spnTolerance:Spinner, view:View, viewColorBand: View, key:String, textView:TextView) {
        val toleranceColors = toleranceColors(view)
        spnTolerance.apply{
            adapter = ColorSpinnerAdapter(context, toleranceColors)
            onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                @SuppressLint("ResourceAsColor")
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    selectedColor = toleranceColors[p2]
                    when (p2) {
                        0 -> viewColorBand.setBackgroundColor(resources.getColor(R.color.white))
                        1 -> viewColorBand.setBackgroundColor(resources.getColor(R.color.brown))
                        2 -> viewColorBand.setBackgroundColor(resources.getColor(R.color.red))
                        3 -> viewColorBand.setBackgroundColor(resources.getColor(R.color.green))
                        4 -> viewColorBand.setBackgroundColor(resources.getColor(R.color.blue))
                        5 -> viewColorBand.setBackgroundColor(resources.getColor(R.color.purple))
                        6 -> viewColorBand.setBackgroundColor(resources.getColor(R.color.gray))
                        7 -> viewColorBand.setBackgroundColor(resources.getColor(R.color.white))
                        8 -> viewColorBand.setBackgroundColor(resources.getColor(R.color.golden))
                        9 -> viewColorBand.setBackgroundColor(resources.getColor(R.color.silver))
                    }
                    prefs.saveName(toleranceColors[p2].peso, key)
                    val mainActivity = MainActivity()
                    mainActivity.print(textView)
                    if(p2 == 0) mainActivity.clearText(textView)
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }
    }
}
