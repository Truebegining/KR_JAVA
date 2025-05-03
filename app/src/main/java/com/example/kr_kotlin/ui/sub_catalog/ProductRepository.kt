package com.example.kr_kotlin.ui.sub_catalog

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject

class ProductRepository {
    private val firestore = FirebaseFirestore.getInstance()
    val auth = FirebaseAuth.getInstance()
    val currentUserId = auth.currentUser?.uid ?: throw IllegalStateException("User not signed in")
    private val productCol = firestore.collection("products")
    private val favCol = firestore.collection("users").document(currentUserId)
        .collection("favorites")

    fun getProducts( callback: (List<Product>) -> Unit) {
        firestore.collection("products").get().addOnSuccessListener { result ->
            val products = result.map { it.toObject(Product::class.java).copy(id = it.id) }
            callback(products)
        }
            .addOnFailureListener {
                callback(emptyList())
            }
    }

    fun addToFavorites (product : Product, onComplete: () -> Unit){
        favCol.document(product.id).set(product).addOnSuccessListener { onComplete }
    }

    fun removeFromFarovires (productId: String, onComplete: () -> Unit) {
        favCol.document(productId).delete().addOnSuccessListener { onComplete }
    }

    fun getFavotites (onResult: (List<Product>) -> Unit) {
        favCol.get().addOnSuccessListener { snap ->
            val favProducts = snap.map { it.toObject(Product::class.java).copy(id = it.id) }
            onResult(favProducts)
        }
            .addOnFailureListener { onResult(emptyList()) }
    }
}