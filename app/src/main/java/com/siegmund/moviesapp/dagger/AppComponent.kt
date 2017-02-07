package com.siegmund.moviesapp.dagger

import com.siegmund.moviesapp.App
import com.siegmund.moviesapp.ui.main.MainPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(app: App)
    fun inject(presenter: MainPresenter)
}