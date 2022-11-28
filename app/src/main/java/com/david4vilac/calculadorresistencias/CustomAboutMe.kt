package com.david4vilac.calculadorresistencias

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.DialogFragment
import com.david4vilac.calculadorresistencias.BandWeight.Companion.prefs

@Suppress("DEPRECATION")
class CustomAboutMe: DialogFragment() {

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,

    ): View? {
        val rootView: View = inflater.inflate(R.layout.about_me_custom_dialog, container, false)
        val uri = Uri.parse ("https://github.com/david4vilac")

        val ivProfile: ImageView = rootView.findViewById(R.id.imageToProfile)

        if(prefs.getTheme() == "light"){
            ivProfile.background = resources.getDrawable(R.drawable.goku_image_1)
        }else if(prefs.getTheme() == "dark"){
            ivProfile.background = resources.getDrawable(R.drawable.imagen_goku_ssj)
        }

        rootView.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        return rootView
    }

}
