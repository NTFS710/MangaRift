package com.sephirita.mangarift

import android.app.Application
import com.sephirita.mangarift.data.di.dataModules
import com.sephirita.mangarift.presentation.di.uiModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApp)
            modules(
                uiModules,
                dataModules
            )
        }
    }
}