package com.siegmund.cleanarchitectureexample

import android.app.Application
import com.siegmund.cleanarchitectureexample.dagger.AppComponent
import com.siegmund.cleanarchitectureexample.dagger.AppModule
import com.siegmund.cleanarchitectureexample.dagger.DaggerAppComponent

class App: Application() {
    private lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    fun component() = component
}