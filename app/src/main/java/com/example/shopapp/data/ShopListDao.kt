package com.example.shopapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ShopListDao {
    @Query("SELECT * FROM shop_items")
    fun getShopList() : LiveData<List<ShopItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addShopItem(shopItemDbModel: ShopItemDbModel)

    @Query("DELETE FROM shop_items where id=:shopItemId")
    fun deleteShopItem(shopItemId : Int)

    @Query("DELETE FROM shop_items where id=:shopItemId LIMIT 1")
    fun getShopItem(shopItemId : Int) : ShopItemDbModel

}