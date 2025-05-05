package com.example.kr_kotlin.ui.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kr_kotlin.R
import com.example.kr_kotlin.databinding.FragmentCatalogBinding
import com.example.kr_kotlin.databinding.FragmentFavoritesBinding
import com.example.kr_kotlin.ui.sub_catalog.ProductAdapter
import com.example.kr_kotlin.ui.sub_catalog.ProductViewModel


class FavoritesFragment : Fragment() {

    private var __binding : FragmentFavoritesBinding? = null
    private val mBinding get() = __binding!!

    private lateinit var viewModel : ProductViewModel
    private lateinit var adapter: ProductAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        __binding = FragmentFavoritesBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        adapter = ProductAdapter(emptyList(), emptyList()) {product ->
            viewModel.toggleFavorite(product)
        }

        val recyclerView = mBinding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = adapter

        viewModel.favorites.observe (viewLifecycleOwner) {
            adapter.updateData(it)
            adapter.updateFavData(it)
        }

        viewModel.loadFavorites()

    }
}