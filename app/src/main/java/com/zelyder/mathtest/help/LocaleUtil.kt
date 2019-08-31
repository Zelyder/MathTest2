package com.zelyder.mathtest.help

import android.content.Context
import android.content.res.Configuration
import androidx.core.os.ConfigurationCompat.getLocales
import android.os.Build
import java.util.*


class LocaleUtil {
    fun invalidateCurrentLocale(context: Context) {
        updateResources(
            context,
            getConfigLocale(context.resources.configuration)
        )
    }

    fun getConfigLocale(configuration: Configuration): Locale {
        return if (Build.VERSION.SDK_INT < 24) {
            configuration.locale
        } else {
            configuration.locales.get(0)
        }
    }

//    private fun getLocaleOrDefault(locale: Locale): Locale {
//        return if (AVAILABLE_LOCALES.contains(locale)) {
//            locale
//        } else DEFAULT_LOCALE
//    }

    private fun updateResources(context: Context, locale: Locale) {
        Locale.setDefault(locale)

        val res = context.resources
        val config = Configuration(res.configuration)
        config.setLocale(locale)
        res.updateConfiguration(config, res.displayMetrics)
    }
}