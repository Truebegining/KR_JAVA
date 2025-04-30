package com.example.kr_kotlin.ui.sub_catalog

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore

class ProductRepository {
    private val firestore = FirebaseFirestore.getInstance()

    fun getProducts( callback: (List<Product>) -> Unit) {
        firestore.collection("products").get().addOnSuccessListener { result ->
            val products = result.map { it.toObject(Product::class.java).copy(id = it.id) }
            callback(products)
        }
            .addOnFailureListener {
                callback(emptyList())
            }
    }
}