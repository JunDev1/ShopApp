package com.example.shopapp.domain

import javax.inject.Inject

class ModifyShopItemUseCase @Inject constructor(private val shopListRepository: ShopListRepository) {
    suspend fun editShopListItem(shopItem: ShopItem) {
        shopListRepository.editShopItem(shopItem)
    }
}