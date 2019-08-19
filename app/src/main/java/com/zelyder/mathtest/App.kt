package com.zelyder.mathtest

import android.app.Application

class App : Application(){

    companion object {
        //platformStatic allow access it from java code
        @JvmStatic lateinit var graph: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        graph = DaggerAppComponent.builder().build()
        graph.inject(this)
    }


}