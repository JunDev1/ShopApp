package com.example.shopapp.domain

class DeleteShopListUseCase(private val shopListRepository: ShopListRepository) {
    fun deleteShopListItem(shopItem: ShopItem)  {
        shopListRepository.deleteShopListItem(shopItem)
    }
}