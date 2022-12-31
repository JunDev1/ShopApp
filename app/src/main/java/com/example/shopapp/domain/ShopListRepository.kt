package com.example.shopapp.domain

interface ShopListRepository {

    fun addShopItem(shopItem: ShopItem)

    fun deleteShopListItem(shopItem: ShopItem)

    fun getShopList() : List<ShopItem>

    fun modifyShopListItem(shopItem: ShopItem)

    fun getShopItemId(shopItemId : Int) : ShopItem
}