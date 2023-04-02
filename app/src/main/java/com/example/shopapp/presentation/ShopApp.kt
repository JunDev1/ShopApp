package com.example.shopapp.presentation

import android.app.Application
import com.example.shopapp.di.DaggerApplicationComponent

class ShopApp : Application() {
    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}