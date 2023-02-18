package com.example.shopapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shopapp.data.ShopListRepositoryImpl
import com.example.shopapp.domain.AddShopItemUseCase
import com.example.shopapp.domain.GetShopItemUseCase
import com.example.shopapp.domain.ModifyShopItemUseCase
import com.example.shopapp.domain.ShopItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class ShopItemViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ShopListRepositoryImpl(application)
    private val getShopItemUseCase = GetShopItemUseCase(repository)
    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val editShopItemUseCase = ModifyShopItemUseCase(repository)

    private val scope = CoroutineScope(Dispatchers.IO)

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean> get() = _errorInputName

    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean> get() = _errorInputCount //урок 5.3

    private val _shopItem = MutableLiveData<ShopItem>()
    val shopItem: LiveData<ShopItem> get() = _shopItem

    private val _closedObject = MutableLiveData<Unit>()
    val closedObject: LiveData<Unit> get() = _closedObject

    fun getShopItem(shopItemId: Int) {
        scope.launch {
            val item = getShopItemUseCase.getShopItemId(shopItemId)
            _shopItem.value = item
        }
    }

    fun addShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldValid = validateInput(name, count)
        if (fieldValid) {
            scope.launch {
                val shopItem = ShopItem(name, count, true)
                addShopItemUseCase.addShopItem(shopItem)
                finishWork()
            }
        }
    }

    private fun finishWork() {
        _closedObject.value = Unit
    }

    fun editShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldValid = validateInput(name, count)
        if (fieldValid) {
            scope.launch {
                _shopItem.value?.let {
                    val item = it.copy(name = name, count = count)
                    editShopItemUseCase.editShopListItem(item)
                    finishWork()
                }
            }
        }
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun validateInput(name: String, count: Int): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if (count <= 0) {
            _errorInputCount.value = true
            return false
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    fun resetErrorInputCount() {
        _errorInputCount.value = false
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}