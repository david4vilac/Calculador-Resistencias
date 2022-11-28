package com.david4vilac.calculadorresistencias


import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.PreferenceFragmentCompat


class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        val homeFragment = HomeFragment()
        homeFragment.loadSettings()

        super.onConfigurationChanged(newConfig)
    }



    /*private fun restartApplication(){
        val i = view?.Intent(this, SettingsFragment::class.java)
        startActivity(i)
        activity?.finish()
    }*/
}