package com.example.shopapp.domain

import javax.inject.Inject

class DeleteShopListUseCase @Inject constructor(private val shopListRepository: ShopListRepository) {
    suspend fun deleteShopListItem(shopItem: ShopItem)  {
        shopListRepository.deleteShopItem(shopItem)
    }
}