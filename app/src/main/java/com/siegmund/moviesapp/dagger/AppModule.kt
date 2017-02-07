package com.siegmund.moviesapp.dagger

import android.content.Context
import com.siegmund.moviesapp.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = arrayOf(NetworkModule::class))
class AppModule(private val app: App) {
    @Provides @Singleton
    fun provideAppContext(): Context = app
}