package com.example.shopapp.di

import android.app.Activity
import android.app.Application
import com.example.shopapp.presentation.MainActivity
import com.example.shopapp.presentation.ShopItemFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Inject


@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(activity: MainActivity)
    fun inject(fragment: ShopItemFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance
            application: Application
        ) : ApplicationComponent
    }
}