package com.example.shopapp.di

import android.app.Application
import com.example.shopapp.data.AppDatabase
import com.example.shopapp.data.ShopListDao
import com.example.shopapp.data.ShopListRepositoryImpl
import com.example.shopapp.domain.ShopListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {
    @ApplicationScope
    @Binds
    fun bindShopListRepository(impl : ShopListRepositoryImpl) : ShopListRepository

    companion object {
        @ApplicationScope
        @Provides
        fun provideShopListDao(application: Application) : ShopListDao {
            return AppDatabase.getInstance(application).shopListDao()
        }
    }
}