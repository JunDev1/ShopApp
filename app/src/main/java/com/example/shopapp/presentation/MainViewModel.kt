package com.example.shopapp.presentation

import androidx.lifecycle.ViewModel
import com.example.shopapp.domain.DeleteShopListUseCase
import com.example.shopapp.domain.GetShopListUseCase
import com.example.shopapp.domain.ModifyShopItemUseCase
import com.example.shopapp.domain.ShopItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getShopItemUseCase: GetShopListUseCase,
    private val deleteShopItemUseCase: DeleteShopListUseCase,
    private val modifyShopItemUseCaseUseCase: ModifyShopItemUseCase,
) : ViewModel() {

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