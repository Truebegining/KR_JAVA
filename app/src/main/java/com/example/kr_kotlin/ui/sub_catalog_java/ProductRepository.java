package com.example.kr_kotlin.ui.sub_catalog_java;

import com.example.kr_kotlin.ui.cart_java.Order;
import com.example.kr_kotlin.ui.cart_java.OrderItem;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private final FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private final FirebaseAuth auth = FirebaseAuth.getInstance();
    private final String currentUserId = auth.getCurrentUser() != null ? auth.getCurrentUser().getUid() : null;

    private final Query productCol = firestore.collection("products");
    private final Query favCol = firestore.collection("users").document(currentUserId).collection("favorites");
    private final Query cartCol = firestore.collection("users").document(currentUserId).collection("cart");
    private final Query orderCol = firestore.collection("users").document(currentUserId).collection("orders");

    public interface ProductListCallback {
        void onComplete(List<Product> products);
    }

    public interface SimpleCallback {
        void onComplete();
    }

    public void getProducts(String category, String search, ProductListCallback callback) {
        Query query = firestore.collection("products");
        if (category != null && !category.isEmpty()) {
            query = query.whereEqualTo("category", category);
        }

        query.get().addOnSuccessListener(result -> {
            List<Product> products = new ArrayList<>();
            result.forEach(document -> {
                Product product = document.toObject(Product.class);
                product.setId(document.getId());
                products.add(product);
            });
            callback.onComplete(products);
        }).addOnFailureListener(e -> callback.onComplete(new ArrayList<>()));
    }

    public void addToFavorites(Product product, SimpleCallback onComplete) {
        firestore.collection("users").document(currentUserId)
                .collection("favorites").document(product.getId())
                .set(product)
                .addOnSuccessListener(unused -> onComplete.onComplete());
    }

    public void removeFromFavorites(String productId, SimpleCallback onComplete) {
        firestore.collection("users").document(currentUserId)
                .collection("favorites").document(productId)
                .delete()
                .addOnSuccessListener(unused -> onComplete.onComplete());
    }

    public void removeFromCart(String productId, SimpleCallback onComplete) {
        firestore.collection("users").document(currentUserId)
                .collection("cart").document(productId)
                .delete()
                .addOnSuccessListener(unused -> onComplete.onComplete());
    }

    public void addToCart(Product product, SimpleCallback onComplete) {
        firestore.collection("users").document(currentUserId)
                .collection("cart").document(product.getId())
                .set(product)
                .addOnSuccessListener(unused -> onComplete.onComplete());
    }

    public void order(List<Product> productList, SimpleCallback onComplete) {
        List<OrderItem> items = new ArrayList<>();
        for (Product product : productList) {
            items.add(new OrderItem(product.getArticle(), product.getName(), product.getPrice()));
        }

        String orderId = firestore.collection("users").document(currentUserId)
                .collection("orders").document().getId();

        Order order = new Order(orderId, Timestamp.now(), items);

        firestore.collection("users").document(currentUserId)
                .collection("orders").document(orderId)
                .set(order)
                .addOnSuccessListener(unused -> onComplete.onComplete());
    }

    public void getFavorites(ProductListCallback onResult) {
        firestore.collection("users").document(currentUserId)
                .collection("favorites")
                .get()
                .addOnSuccessListener(result -> {
                    List<Product> products = new ArrayList<>();
                    result.forEach(document -> {
                        Product product = document.toObject(Product.class);
                        product.setId(document.getId());
                        products.add(product);
                    });
                    onResult.onComplete(products);
                })
                .addOnFailureListener(e -> onResult.onComplete(new ArrayList<>()));
    }

    public void getCart(ProductListCallback onResult) {
        firestore.collection("users").document(currentUserId)
                .collection("cart")
                .get()
                .addOnSuccessListener(result -> {
                    List<Product> products = new ArrayList<>();
                    result.forEach(document -> {
                        Product product = document.toObject(Product.class);
                        product.setId(document.getId());
                        products.add(product);
                    });
                    onResult.onComplete(products);
                })
                .addOnFailureListener(e -> onResult.onComplete(new ArrayList<>()));
    }
}
