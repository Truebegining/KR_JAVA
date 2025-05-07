package com.example.kr_kotlin.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.kr_kotlin.ui.sub_catalog.Product
import com.example.kr_kotlin.ui.sub_catalog.ProductRepository

class CartViewModel : ViewModel() {
    private val repository = ProductRepository()

    private val _cart = MutableLiveData<List<Product>>()
    val cart : LiveData<List<Product>> get() = _cart

    fun delFromCart (product: Product) {
        repository.removeFromCart(product.id) {
            loadCart()
        }

    }

    fun buyButton(product: Product) {
        repository.addToCart(product) {
            loadCart()
        }
    }

//    fun orderButton() {
//        val currentCart = _cart.value.orEmpty()
//        if (currentCart.isEmpty()) return
//
//        repository.order(currentCart) {
//            clearCart()
//        }
//    }

    fun orderButton() {
        repository.order(_cart.value.orEmpty()) {
            clearCart()
        }
    }

    fun clearCart() {
        _cart.value?.forEach { repository.removeFromCart(it.id) { loadCart() } } // мб оптимизорвать:
    // вызвать метод loadCart() 1 раз в конце.
    }

    fun loadCart() {
        repository.getCart { _cart.value =  it}
    }
}