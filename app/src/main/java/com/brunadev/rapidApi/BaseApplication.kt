package com.brunadev.rapidApi

import android.app.Application
import com.brunadev.rapidApi.di.AppModule.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication: Application() {

    companion object{
        private lateinit var instance: BaseApplication
    }

    override fun onCreate() {
        super.onCreate()

        instance = this@BaseApplication

        startKoin{
            androidContext(this@BaseApplication)
            modules(appModule)
        }
    }
}