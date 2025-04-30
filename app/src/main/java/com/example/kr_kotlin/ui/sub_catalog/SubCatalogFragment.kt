package com.example.kr_kotlin.ui.sub_catalog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
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

    private lateinit var viewModel: ProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        __binding = FragmentSubCatalogBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        productAdapter = ProductAdapter(emptyList())

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(context, 2) // Сетка 2 столбца
        recyclerView.adapter = productAdapter

        viewModel.products.observe(viewLifecycleOwner) {
            productAdapter.updateData(it)
        }

        viewModel.fetchProducts()
//
//        val productList = listOf(
//            Product(
//                article = "5060068",
//                imageUrl = "https://c.dns-shop.ru/thumb/st1/fit/500/500/847aa6e2dbd0acf3e9a7dd0f57297945/dab72103be8217ffb1c0413dce2567aaa7d3c08f4538e1a0ad4cefd8f2676976.jpg.webp",
//                dimensions = "80*200*40",
//                price = "15000₽",
//                name = "Шкаф белый"
//            ),
//            Product(
//                article = "5060069",
//                imageUrl = "https://c.dns-shop.ru/thumb/st1/fit/320/250/a5a137d5b71d32c4c2418a24e717578b/5289e41b41daa2df0f0ce674a494ea63294c9ce2e61b20086738edd9779ac7d7.jpg",
//                dimensions = "120*200*50",
//                price = "20000₽",
//                name = "Шкаф черный"
//            ),
//            Product(
//                article = "5060069",
//                imageUrl = "https://hoff.ru/upload/iblock/087/dfs0fmx9i5k38sozsn4xc5ezd8o11oyw.jpg",
//                dimensions = "120*200*50",
//                price = "20000₽",
//                name = "Шкаф черный"
//            ),
//            Product(
//                article = "5060068",
//                imageUrl = "https://c.dns-shop.ru/thumb/st1/fit/500/500/847aa6e2dbd0acf3e9a7dd0f57297945/dab72103be8217ffb1c0413dce2567aaa7d3c08f4538e1a0ad4cefd8f2676976.jpg.webp",
//                dimensions = "80*200*40",
//                price = "15000₽",
//                name = "Шкаф белый"
//            ),
//            Product(
//                article = "5060069",
//                imageUrl = "https://c.dns-shop.ru/thumb/st1/fit/320/250/a5a137d5b71d32c4c2418a24e717578b/5289e41b41daa2df0f0ce674a494ea63294c9ce2e61b20086738edd9779ac7d7.jpg",
//                dimensions = "120*200*50",
//                price = "20000₽",
//                name = "Шкаф черный"
//            ),
//            Product(
//                article = "5060069",
//                imageUrl = "https://hoff.ru/upload/iblock/087/dfs0fmx9i5k38sozsn4xc5ezd8o11oyw.jpg",
//                dimensions = "120*200*50",
//                price = "20000₽",
//                name = "Шкаф черный"
//            ),Product(
//                article = "5060068",
//                imageUrl = "https://c.dns-shop.ru/thumb/st1/fit/500/500/847aa6e2dbd0acf3e9a7dd0f57297945/dab72103be8217ffb1c0413dce2567aaa7d3c08f4538e1a0ad4cefd8f2676976.jpg.webp",
//                dimensions = "80*200*40",
//                price = "15000₽",
//                name = "Шкаф белый"
//            ),
//            Product(
//                article = "5060069",
//                imageUrl = "https://c.dns-shop.ru/thumb/st1/fit/320/250/a5a137d5b71d32c4c2418a24e717578b/5289e41b41daa2df0f0ce674a494ea63294c9ce2e61b20086738edd9779ac7d7.jpg",
//                dimensions = "120*200*50",
//                price = "20000₽",
//                name = "Шкаф черный"
//            ),
//            Product(
//                article = "5060069",
//                imageUrl = "https://hoff.ru/upload/iblock/087/dfs0fmx9i5k38sozsn4xc5ezd8o11oyw.jpg",
//                dimensions = "120*200*50",
//                price = "20000₽",
//                name = "Шкаф черный"
//            ),


            // Добавляешь сколько хочешь товаров

//        productAdapter = ProductAdapter(productList)
//        recyclerView.adapter = productAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        __binding = null
    }

}