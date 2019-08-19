package com.zelyder.mathtest

import com.zelyder.mathtest.data.room.RoomModule
import com.zelyder.mathtest.ui.activities.MainActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component (modules = arrayOf(RoomModule::class))
interface AppComponent {
    fun inject(application: App)

    fun inject(mainActivity: MainActivity)
}