package com.example.kr_kotlin.ui.cart_java;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.kr_kotlin.ui.sub_catalog_java.Product;
import com.example.kr_kotlin.ui.sub_catalog_java.ProductRepository;

import java.util.List;

public class CartViewModel extends ViewModel {

    private final ProductRepository repository = new ProductRepository();

    private final MutableLiveData<List<Product>> _cart = new MutableLiveData<>();
    public LiveData<List<Product>> cart = _cart;

    public void delFromCart(Product product) {
        repository.removeFromCart(product.getId(), this::loadCart);
    }

    public void buyButton(Product product) {
        repository.addToCart(product, this::loadCart);
    }

    public void orderButton() {
        repository.order(_cart.getValue() != null ? _cart.getValue() : List.of(), this::clearCart);
    }

    public void clearCart() {
        List<Product> currentCart = _cart.getValue();
        if (currentCart != null) {
            for (Product product : currentCart) {
                repository.removeFromCart(product.getId(), this::loadCart);
            }
        }
    }

    public void loadCart() {
        repository.getCart(cart -> _cart.setValue(cart));
    }
}
