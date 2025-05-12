package com.example.kr_kotlin.ui.cart_java;

public class OrderItem {

    private String article;
    private String name;
    private String price;

    // Конструктор по умолчанию
    public OrderItem() {
        this.article = "";
        this.name = "";
        this.price = "";
    }

    public OrderItem(String article, String name, String price) {
        this.article = article;
        this.name = name;
        this.price = price;
    }

    // Геттеры и сеттеры
    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}