package com.example.shopapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shopapp.data.ShopListRepositoryImpl
import com.example.shopapp.domain.*

class MainViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopItemUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopListUseCase(repository)
    private val modifyShopItemUseCaseUseCase = ModifyShopListUseCase(repository)

    val shopList = MutableLiveData<List<ShopItem>>()

    fun getShopList() {
        val list = getShopItemUseCase.getShopList()
        shopList.value = list
    }

    fun deleteShopList(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopListItem(shopItem)
        getShopList()
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        modifyShopItemUseCaseUseCase.modifyShopListItem(newItem)
        getShopList()
    }
}