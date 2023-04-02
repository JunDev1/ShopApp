package com.example.shopapp.di

import androidx.lifecycle.ViewModel
import com.example.shopapp.presentation.MainViewModel
import com.example.shopapp.presentation.ShopItemViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel : MainViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ShopItemViewModel::class)
    fun bindShopItemViewModel(viewModel : ShopItemViewModel) : ViewModel
}