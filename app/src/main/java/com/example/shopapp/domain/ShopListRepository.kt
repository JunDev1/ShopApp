package com.example.shopapp.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {

    fun addShopItem(shopItem: ShopItem)

    fun deleteShopListItem(shopItem: ShopItem)

    fun getShopList() : LiveData<List<ShopItem>>

    fun modifyShopListItem(shopItem: ShopItem)

    fun getShopItemId(shopItemId : Int) : ShopItem
}