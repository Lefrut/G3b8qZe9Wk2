package com.binet.G3b8qZe9Wk2

import android.app.Application
import com.binet.G3b8qZe9Wk2.di.domainModule
import com.binet.G3b8qZe9Wk2.di.serviceModule
import com.binet.G3b8qZe9Wk2.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(serviceModule, domainModule, viewModelModule)
        }
    }

}