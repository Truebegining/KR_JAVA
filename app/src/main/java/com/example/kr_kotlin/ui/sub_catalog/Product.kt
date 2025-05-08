package com.example.kr_kotlin.ui.sub_catalog

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val article: String = "",
    val imageUrl: String = "",
    val dimensions: String = "",
    val price: String = "",
    val name: String = "",
    val id: String = ""
) : Parcelable