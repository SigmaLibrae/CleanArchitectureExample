package com.siegmund.moviesapp

import android.app.Application
import com.siegmund.moviesapp.dagger.AppComponent
import com.siegmund.moviesapp.dagger.AppModule
import com.siegmund.moviesapp.dagger.DaggerAppComponent

class App: Application() {
    private lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    fun component() = component
}
