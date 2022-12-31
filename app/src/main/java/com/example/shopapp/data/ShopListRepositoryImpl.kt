package com.example.shopapp.data

import com.example.shopapp.domain.ShopItem
import com.example.shopapp.domain.ShopListRepository

object ShopListRepositoryImpl : ShopListRepository {

    private val shopList = mutableListOf<ShopItem>()
    private var autoIncrementId = 0

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
    }

    override fun deleteShopListItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
    }

    override fun getShopList(): List<ShopItem> {
        return shopList.toList()
    }

    override fun modifyShopListItem(shopItem: ShopItem) {
        val oldElement = getShopItemId(shopItem.id)
        shopList.remove(oldElement)
        addShopItem(shopItem)
    }

    override fun getShopItemId(shopItemId: Int): ShopItem {
        return shopList.find {
            it.id == shopItemId
        } ?: throw RuntimeException("Element with $shopItemId not found")
    }
}