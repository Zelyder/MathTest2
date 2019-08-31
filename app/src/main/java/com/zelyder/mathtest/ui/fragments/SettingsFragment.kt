package com.zelyder.mathtest.ui.fragments


import android.content.Intent
import android.os.Bundle
import android.preference.ListPreference

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.zelyder.mathtest.R
import com.zelyder.mathtest.ui.activities.MainActivity

/**
 * A simple [Fragment] subclass.
 */
class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.pref)
        activity?.findViewById<Button>(R.id.button)?.visibility = View.GONE

        val listLang: Preference? =  findPreference("pref_lang")

        listLang?.setOnPreferenceChangeListener {_,_ ->
            restartApp()
            true
        }
    }

    internal fun restartApp() {
        val intent = Intent(activity, MainActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }



}
