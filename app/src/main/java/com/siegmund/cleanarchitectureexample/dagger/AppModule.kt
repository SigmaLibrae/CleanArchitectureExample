package com.siegmund.cleanarchitectureexample.dagger

import android.content.Context
import com.siegmund.cleanarchitectureexample.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = arrayOf(NetworkModule::class))
class AppModule(private val app: App) {
    @Provides @Singleton
    fun provideAppContext(): Context = app
}