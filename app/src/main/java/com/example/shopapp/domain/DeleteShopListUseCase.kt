package com.example.shopapp.domain

class DeleteShopListUseCase(private val shopListRepository: ShopListRepository) {
    suspend fun deleteShopListItem(shopItem: ShopItem)  {
        shopListRepository.deleteShopItem(shopItem)
    }
}