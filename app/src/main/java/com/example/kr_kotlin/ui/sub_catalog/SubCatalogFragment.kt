package com.example.kr_kotlin.ui.sub_catalog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kr_kotlin.R
import com.example.kr_kotlin.databinding.FragmentSubCatalogBinding
import com.example.kr_kotlin.ui.cart.CartViewModel
import com.google.android.play.integrity.internal.ac


class SubCatalogFragment : Fragment() {

    private var __binding : FragmentSubCatalogBinding? = null
    private val mBinding get() = __binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter
    private lateinit var productViewModel: ProductViewModel
    private lateinit var cartViewModel: CartViewModel
    private val args : SubCatalogFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        __binding = FragmentSubCatalogBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val category = args.category
        productViewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        cartViewModel = ViewModelProvider(this)[CartViewModel::class.java]
        adapter = ProductAdapter(emptyList(), emptyList(), {product ->
            productViewModel.toggleFavorite(product)}, {
            cartViewModel.buyButton(it) },) {
            val action = SubCatalogFragmentDirections
                .actionSubCatalogFragmentToProductCardfFragment(it, productViewModel)
            findNavController().navigate(action)
        }

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(context, 2) // Сетка 2 столбца
        recyclerView.adapter = adapter

        productViewModel.products.observe(viewLifecycleOwner) {
            adapter.updateData(it)

        }
        productViewModel.favorites.observe (viewLifecycleOwner) {

            adapter.updateFavData(it)
        }
        

        productViewModel.fetchProducts(category)
        productViewModel.loadFavorites()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        __binding = null
    }

}