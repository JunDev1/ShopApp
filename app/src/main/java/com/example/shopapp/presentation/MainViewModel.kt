package com.example.shopapp.presentation

import androidx.lifecycle.ViewModel
import com.example.shopapp.data.ShopListRepositoryImpl
import com.example.shopapp.domain.*

class MainViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopItemUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopListUseCase(repository)
    private val modifyShopItemUseCaseUseCase = ModifyShopItemUseCase(repository)

    val shopList = getShopItemUseCase.getShopList()

    fun deleteShopList(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopListItem(shopItem)
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        modifyShopItemUseCaseUseCase.modifyShopListItem(newItem)
    }
}