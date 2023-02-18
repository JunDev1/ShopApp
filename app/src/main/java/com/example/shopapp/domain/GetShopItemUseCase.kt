package com.example.shopapp.domain

class GetShopItemUseCase(private val shopListRepository: ShopListRepository) {
    suspend fun getShopItemId(shopItemId : Int) : ShopItem {
        return shopListRepository.getShopItem(shopItemId)
    }
}