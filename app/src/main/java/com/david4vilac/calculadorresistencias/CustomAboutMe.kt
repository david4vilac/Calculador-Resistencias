package com.david4vilac.calculadorresistencias

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

class CustomAboutMe: DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,

    ): View? {
        val rootView: View = inflater.inflate(R.layout.about_me_custom_dialog, container, false)
        val uri = Uri.parse ("https://github.com/david4vilac")
        rootView.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        return rootView
    }

}
