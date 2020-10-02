package com.sun.mvvm

import android.app.Application
import com.sun.mvvm.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(listOf(repositoryModule, viewModelModule, apiModule, retrofitModule, databaseModule))
        }
    }
}
