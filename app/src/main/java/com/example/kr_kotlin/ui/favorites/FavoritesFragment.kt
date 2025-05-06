package com.example.kr_kotlin.ui.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kr_kotlin.databinding.FragmentFavoritesBinding
import com.example.kr_kotlin.ui.cart.CartViewModel
import com.example.kr_kotlin.ui.sub_catalog.ProductAdapter
import com.example.kr_kotlin.ui.sub_catalog.ProductViewModel


class FavoritesFragment : Fragment() {

    private var __binding : FragmentFavoritesBinding? = null
    private val mBinding get() = __binding!!

    private lateinit var productViewModel : ProductViewModel
    private lateinit var adapter: ProductAdapter
    private lateinit var cartViewModel: CartViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        __binding = FragmentFavoritesBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productViewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        cartViewModel = ViewModelProvider(this)[CartViewModel::class.java]
        adapter = ProductAdapter(emptyList(), emptyList(), {product ->
            productViewModel.toggleFavorite(product)} ) {
            cartViewModel.buyButton(it)
        }
        val recyclerView = mBinding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = adapter

        productViewModel.favorites.observe (viewLifecycleOwner) {
            adapter.updateData(it)
            adapter.updateFavData(it)
        }

        productViewModel.loadFavorites()

    }
}