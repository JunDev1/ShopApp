package com.example.shopapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.shopapp.data.ShopListRepositoryImpl
import com.example.shopapp.domain.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ShopListRepositoryImpl(application)

    private val getShopItemUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopListUseCase(repository)
    private val modifyShopItemUseCaseUseCase = ModifyShopItemUseCase(repository)

    private val scope = CoroutineScope(Dispatchers.Default)

    val shopList = getShopItemUseCase.getShopList()

    fun deleteShopList(shopItem: ShopItem) {
        scope.launch {
            deleteShopItemUseCase.deleteShopListItem(shopItem)
        }
    }

    fun changeEnableState(shopItem: ShopItem) {
        scope.launch {
            val newItem = shopItem.copy(enabled = !shopItem.enabled)
            modifyShopItemUseCaseUseCase.editShopListItem(newItem)
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}