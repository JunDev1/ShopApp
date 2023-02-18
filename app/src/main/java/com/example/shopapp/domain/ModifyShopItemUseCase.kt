package com.example.shopapp.domain

class ModifyShopItemUseCase(private val shopListRepository: ShopListRepository) {
    suspend fun editShopListItem(shopItem: ShopItem) {
        shopListRepository.editShopItem(shopItem)
    }
}