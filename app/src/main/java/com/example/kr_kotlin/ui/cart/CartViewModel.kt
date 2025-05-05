package com.example.kr_kotlin.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kr_kotlin.ui.sub_catalog.Product
import com.example.kr_kotlin.ui.sub_catalog.ProductRepository

class CartViewModel : ViewModel() {
    private val repository = ProductRepository()

    private val _cart = MutableLiveData<List<Product>>()
    val cart : LiveData<List<Product>> get() = _cart

    fun delFromCart (product: Product) {

    }

    fun loadCart() {
        repository.getCart { _cart.value =  it}
    }
}