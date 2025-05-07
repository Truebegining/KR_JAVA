package com.example.kr_kotlin.ui.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kr_kotlin.R
import com.example.kr_kotlin.databinding.FragmentCartBinding
import com.example.kr_kotlin.ui.sub_catalog.ProductAdapter
import com.example.kr_kotlin.ui.sub_catalog.ProductViewModel

class CartFragment : Fragment() {

    private var __binding : FragmentCartBinding? = null
    private val mBinding get() = __binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CartAdapter
    private lateinit var viewModel: CartViewModel
    private lateinit var orderButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        __binding = FragmentCartBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[CartViewModel::class.java]
        adapter = CartAdapter(emptyList(), {viewModel.delFromCart(it)})

        recyclerView = mBinding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = adapter
        orderButton = mBinding.orderButton

        orderButton.setOnClickListener {

        }

        viewModel.cart.observe(viewLifecycleOwner) {
            adapter.updateData(it)
        }

        viewModel.loadCart()
    }

}