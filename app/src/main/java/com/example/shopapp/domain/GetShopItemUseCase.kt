package com.example.shopapp.domain

import javax.inject.Inject

class GetShopItemUseCase @Inject constructor(private val shopListRepository: ShopListRepository) {
    suspend fun getShopItemId(shopItemId : Int) : ShopItem {
        return shopListRepository.getShopItem(shopItemId)
    }
}