package com.siegmund.cleanarchitectureexample.dagger

import com.siegmund.cleanarchitectureexample.App
import com.siegmund.cleanarchitectureexample.ui.main.MainPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(app: App)
    fun inject(presenter: MainPresenter)
}