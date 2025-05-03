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
import com.example.kr_kotlin.databinding.FragmentSubCatalogBinding


class SubCatalogFragment : Fragment() {

    private var __binding : FragmentSubCatalogBinding? = null
    private val mBinding get() = __binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter

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
        adapter = ProductAdapter(emptyList()) { product ->
            viewModel.toggleFavorite(product) // вызываем метод для смены значка на избранное/неизбранное
        }

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(context, 2) // Сетка 2 столбца
        recyclerView.adapter = adapter

        viewModel.products.observe(viewLifecycleOwner) {
            adapter.updateData(it)
        }

        viewModel.fetchProducts()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        __binding = null
    }

}