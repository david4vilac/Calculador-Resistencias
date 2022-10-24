package com.david4vilac.calculadorresistencias


import android.content.res.Configuration
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat


class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)


    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }



    /*private fun restartApplication(){
        val i = view?.Intent(this, SettingsFragment::class.java)
        startActivity(i)
        activity?.finish()
    }*/
}