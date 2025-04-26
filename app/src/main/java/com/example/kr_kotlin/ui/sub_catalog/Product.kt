package com.example.kr_kotlin.ui.sub_catalog

data class Product (
    val article: String,       // Артикул или id
    val imageUrl: String,       // Ссылка на картинку
    val dimensions: String,     // Размеры (ширина*высота*глубина)
    val price: String,          // Цена
    val name: String            // Название товара
)