package com.example.shopapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shopapp.domain.ShopItem
import com.example.shopapp.domain.ShopListRepository

object ShopListRepositoryImpl : ShopListRepository {

    private val shopListLD = MutableLiveData<List<ShopItem>>()
    private val shopList = sortedSetOf<ShopItem>(Comparator<ShopItem> { p0, p1 ->
        p0.id.compareTo(p1.id)
    })
    private var autoIncrementId = 0

    init {
        for (i in 0 until 100) {
            val item = ShopItem("Name $i", i, kotlin.random.Random.nextBoolean())
            addShopItem(item)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        updateList()
    }

    override fun deleteShopListItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLD
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
    private fun updateList() {
        shopListLD.value = shopList.toList()
    }
}