package com.david4vilac.calculadorresistencias

class ColorObject(var name: String, var hex: Int, var contrastHex: String, peso: String)
{
    val hexHash : Int = hex
    val contrastHexHash : String = "#$contrastHex"
    var peso = "$peso"
}
