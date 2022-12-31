package com.example.shopapp.domain

class ModifyShopListUseCase(private val shopListRepository: ShopListRepository) {
    fun modifyShopListItem(shopItem: ShopItem) {
        shopListRepository.modifyShopListItem(shopItem)
    }
}