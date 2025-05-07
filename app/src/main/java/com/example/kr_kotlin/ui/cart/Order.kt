package com.example.kr_kotlin.ui.cart

import com.google.firebase.Timestamp

data class OrderItem(
    val article: String = "",
    val name: String = "",
    val price: String = "",
//    val quantity: Int = 1 // количество конкретного товара
)

data class Order(
    val id: String = "",
    val createdAt: Timestamp = Timestamp.now(),
    val items: List<OrderItem> = emptyList(),
//    val total: Double = 0.0 // итоговая цена заказа
)