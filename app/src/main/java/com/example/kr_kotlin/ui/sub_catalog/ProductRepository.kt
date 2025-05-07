package com.example.kr_kotlin.ui.sub_catalog

import com.example.kr_kotlin.ui.cart.CartViewModel
import com.example.kr_kotlin.ui.cart.Order
import com.example.kr_kotlin.ui.cart.OrderItem
import com.google.firebase.Firebase
import com.google.firebase.Timestamp
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
    private val cartCol = firestore.collection("users").document(currentUserId)
        .collection("cart")
    private val orderCol = firestore.collection("users").document(currentUserId)
        .collection("orders")

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

    fun removeFromCart (productId: String, onComplete: () -> Unit) {
        cartCol.document(productId).delete().addOnSuccessListener { onComplete }
    }

    fun addToCart (product: Product, onComplete: () -> Unit) {
        cartCol.document(product.id).set(product).addOnSuccessListener { onComplete }
    }

    fun order(productList: List<Product>, onComplete: () -> Unit) {
        val items = productList.map {
            OrderItem(
                article = it.article,
                name = it.name,
                price = it.price
            )
        }

        val newOrderRef = orderCol.document()

        val order = Order(
            id = newOrderRef.id,
            createdAt = Timestamp.now(),
            items = items
        )

        newOrderRef.set(order).addOnSuccessListener {
            onComplete
        }
    }

    fun getFavotites (onResult: (List<Product>) -> Unit) {
        favCol.get().addOnSuccessListener { snap ->
            val favProducts = snap.map { it.toObject(Product::class.java).copy(id = it.id) }
            onResult(favProducts)
        }
            .addOnFailureListener { onResult(emptyList()) }
    }

    fun getCart (onResult: (List<Product>) -> Unit) {
        cartCol.get().addOnSuccessListener { snap ->
            val cartProducts = snap.map { it.toObject(Product::class.java).copy(id = it.id) }
            onResult(cartProducts)
        }
            .addOnFailureListener { onResult(emptyList()) }
    }
}