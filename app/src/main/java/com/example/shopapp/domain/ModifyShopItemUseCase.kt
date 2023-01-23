package com.example.shopapp.domain

class ModifyShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun modifyShopListItem(shopItem: ShopItem) {
        shopListRepository.modifyShopListItem(shopItem)
    }
}