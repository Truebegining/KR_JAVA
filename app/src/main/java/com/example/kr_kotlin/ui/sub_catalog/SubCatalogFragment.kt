package com.example.kr_kotlin.ui.sub_catalog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kr_kotlin.R
import com.example.kr_kotlin.databinding.FragmentCatalogBinding
import com.example.kr_kotlin.databinding.FragmentSubCatalogBinding


class SubCatalogFragment : Fragment() {

    private var __binding : FragmentSubCatalogBinding? = null
    private val mBinding get() = __binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        __binding = FragmentSubCatalogBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(context, 2) // Сетка 2 столбца

        val productList = listOf(
            Product(
                article = "5060068",
                imageUrl = "https://example.com/image1.jpg",
                dimensions = "80*200*40",
                price = "15000₽",
                name = "Шкаф белый"
            ),
            Product(
                article = "5060069",
                imageUrl = "https://example.com/image2.jpg",
                dimensions = "120*200*50",
                price = "20000₽",
                name = "Шкаф черный"
            ),
            Product(
                article = "5060069",
                imageUrl = "https://example.com/image2.jpg",
                dimensions = "120*200*50",
                price = "20000₽",
                name = "Шкаф черный"
            )
            // Добавляешь сколько хочешь товаров
        )

        productAdapter = ProductAdapter(productList)
        recyclerView.adapter = productAdapter
    }

}