package com.example.shopapp.presentation

import androidx.lifecycle.ViewModel
import com.example.shopapp.data.ShopListRepositoryImpl
import com.example.shopapp.domain.AddShopItemUseCase
import com.example.shopapp.domain.GetShopItemUseCase
import com.example.shopapp.domain.ModifyShopItemUseCase
import com.example.shopapp.domain.ShopItem

class ShopItemViewModel : ViewModel() {
    private val repository = ShopListRepositoryImpl
    private val getShopItemUseCase = GetShopItemUseCase(repository)
    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val editShopItemUseCase = ModifyShopItemUseCase(repository)

    fun getShopItem(shopItemId: Int) {
        val item = getShopItemUseCase.getShopItemId(shopItemId)
    }
    fun addShopItem(inputName : String? , inputCount : String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldValid = validateInput(name,count)
        if (fieldValid) {
            val shopItem = ShopItem(name, count, true)
            addShopItemUseCase.addShopItem(shopItem)
        }
    }
    fun editShopItem(inputName : String? , inputCount : String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldValid = validateInput(name,count)
        if(fieldValid) {
            val shopItem = ShopItem(name,count,true)
            editShopItemUseCase.modifyShopListItem(shopItem)
        }

    }

    private fun parseName(inputName: String?) : String {
        return inputName?.trim() ?: ""
    }
    private fun parseCount(inputCount: String?) : Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }
    private fun validateInput(name: String, count : Int) : Boolean {
        var result = true
        if (name.isBlank()) {
            //todo: show error input name
            result = false
        }
        if (count <= 0) {
            //todo: show error input count
            return false
        }
        return result
    }
}