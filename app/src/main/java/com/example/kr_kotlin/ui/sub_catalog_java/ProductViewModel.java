package com.example.kr_kotlin.ui.sub_catalog_java;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductViewModel extends ViewModel {
    private final ProductRepository repository = new ProductRepository();

    private final MutableLiveData<List<Product>> _products = new MutableLiveData<>();
    public LiveData<List<Product>> getProducts() {
        return _products;
    }

    private final MutableLiveData<List<Product>> _favorites = new MutableLiveData<>();
    public LiveData<List<Product>> getFavorites() {
        return _favorites;
    }

    public void fetchProducts(String category, String search) {
        repository.getProducts(category, search, productList -> {
            if (search != null && !search.isEmpty()) {
                List<Product> filtered = productList.stream()
                        .filter(p -> p.getName().toLowerCase().contains(search.toLowerCase()))
                        .collect(Collectors.toList());
                _products.setValue(filtered);
            } else {
                _products.setValue(productList);
            }
        });
    }

    public void toggleFavorite(Product product) {
        List<Product> currentFavs = _favorites.getValue();
        boolean isFav = currentFavs != null &&
                currentFavs.stream().anyMatch(p -> p.getId().equals(product.getId()));

        if (isFav) {
            repository.removeFromFavorites(product.getId(), this::loadFavorites);
        } else {
            repository.addToFavorites(product, this::loadFavorites);
        }
    }

    public void loadFavorites() {
        repository.getFavorites(products -> _favorites.setValue(products));
    }
}