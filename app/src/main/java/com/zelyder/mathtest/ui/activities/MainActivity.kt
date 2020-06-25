package com.zelyder.mathtest.ui.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import com.yandex.mobile.ads.AdEventListener
import com.yandex.mobile.ads.AdEventListener.SimpleAdEventListener
import com.yandex.mobile.ads.AdRequest
import com.yandex.mobile.ads.AdSize
import com.zelyder.mathtest.R
import com.zelyder.mathtest.data.viewmodels.MainViewModel
import com.zelyder.mathtest.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private var mAdRequest: AdRequest? = null
    private val mainViewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val preferences = PreferenceManager.getDefaultSharedPreferences(this.applicationContext)

        if (preferences.getBoolean("firstStart", true)){

            if(Locale.getDefault().language == "ru" || preferences.getString("pref_lang", "en") == "ru"){
                preferences.edit()
                    .putString("pref_lang", "ru")
                    .apply()
            }else{
                preferences.edit()
                    .putString("pref_lang", "en")
                    .apply()
            }


            preferences.edit()
                .putBoolean("firstStart", false)
                .apply()

        } else {
            setAppLocale(preferences.getString("pref_lang", "en") ?: "en")
        }


        if (preferences.getBoolean("cb_pref_dark_style", false)) {
            setTheme(R.style.darkTheme_NoActionBar)
        } else {
            setTheme(R.style.AppTheme_NoActionBar)
        }

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.lifecycleOwner = this
        binding.viewModel = mainViewModel

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = host.navController



        setupActionBarWithNavController(navController)

        initBannerView()
        refreshBannerAd()

        val handler = Handler()
        val delay = 30000L //milliseconds

        handler.postDelayed(object : Runnable {
            override fun run() {
                if(adView.visibility == View.VISIBLE){
                    refreshBannerAd()
                }
                handler.postDelayed(this, delay)
            }
        }, delay)


    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.main_menu, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if(item.itemId == R.id.to_settings_privacy_policy){
//            startActivity(
//                Intent(
//                    Intent.ACTION_VIEW,
//                    Uri.parse("https://math-test-formulas.flycricket.io/privacy.html")
//                )
//            )
//        }
//
//        return item.onNavDestinationSelected(navController)
//                || super.onOptionsItemSelected(item)
//    }

    override fun onSupportNavigateUp(): Boolean {
        if (adView.visibility == View.GONE) adView.visibility = View.VISIBLE
        return navController.navigateUp()
    }

    private fun setAppLocale(locale: String){
        val dm = resources.displayMetrics
        val config = resources.configuration
        config.setLocale(Locale(locale))
        resources.updateConfiguration(config, dm)

    }

    private fun initBannerView() {
        adView.blockId = resources.getString(R.string.banner_ad_yandex_meditation_id)
        adView.adSize = AdSize.BANNER_320x50
        mAdRequest = AdRequest.builder().build()
        adView.adEventListener = mBannerAdEventListener
    }

    private val mBannerAdEventListener: AdEventListener = object : SimpleAdEventListener() {
        override fun onAdLoaded() {
            adView.visibility = View.VISIBLE
        }
    }

    private fun refreshBannerAd() {
        adView.visibility = View.INVISIBLE
        adView.loadAd(mAdRequest)
    }

    override fun onDestroy() {
        adView.destroy()
        super.onDestroy()
    }

    override fun onPause() {
        adView.pause()
        super.onPause()
    }

    override fun onResume() {
        adView.resume()
        super.onResume()
    }

}