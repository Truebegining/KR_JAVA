package com.example.kr_kotlin.ui.cart_java;

import com.google.firebase.Timestamp;
import java.util.List;



public class Order {

    private String id;
    private Timestamp createdAt;
    private List<OrderItem> items;

    // Конструктор по умолчанию
    public Order() {
        this.id = "";
        this.createdAt = Timestamp.now();
        this.items = null; // можно инициализировать пустым списком, если нужно
    }

    public Order(String id, Timestamp createdAt, List<OrderItem> items) {
        this.id = id;
        this.createdAt = createdAt;
        this.items = items;
    }

    // Геттеры и сеттеры
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}
