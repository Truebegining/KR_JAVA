package com.example.kr_kotlin.ui.sub_catalog

import android.os.Parcelable
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kr_kotlin.R
import com.example.kr_kotlin.ui.cart.CartViewModel
import kotlinx.parcelize.Parcelize

@Parcelize
class ProductViewModel : ViewModel(), Parcelable {
    private val repository = ProductRepository()


    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    private val _favorites = MutableLiveData<List<Product>>()
    val favorites: LiveData<List<Product>> get() = _favorites

    fun fetchProducts(category: String? = null, search: String? = null) {
        repository.getProducts(category, search) { productList ->
            if (search != null) {
                _products.value = productList.filter {
                    it.name.contains(search, ignoreCase = true)
                }
            } else {
                _products.value = productList
            }
        }
    }


    fun toggleFavorite(product: Product) {
        val isFav = _favorites.value?.any {it.id == product.id} == true

        if (isFav) {
            repository.removeFromFarovires(product.id) {
                loadFavorites()
            }
        }
        else {
            repository.addToFavorites(product) {
                loadFavorites()
            }
        }

    }

    fun loadFavorites() {
        repository.getFavotites { _favorites.value = it }
    }
}