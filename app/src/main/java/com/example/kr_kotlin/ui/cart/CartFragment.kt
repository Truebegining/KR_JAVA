package com.example.kr_kotlin.ui.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kr_kotlin.R
import com.example.kr_kotlin.databinding.FragmentCartBinding
import com.example.kr_kotlin.ui.sub_catalog.ProductAdapter
import com.example.kr_kotlin.ui.sub_catalog.ProductViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

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
        adapter = CartAdapter(emptyList(), {viewModel.delFromCart(it)}, {
            val action = CartFragmentDirections
                .actionCartFragmentToProductCardfFragment(it)
            findNavController().navigate(action)
        })

        recyclerView = mBinding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = adapter
        orderButton = mBinding.orderButton

        orderButton.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Подтвердите заказ")
                .setMessage("Вы уверены, что хотите оформить заказ?")
                .setNegativeButton("Отмена") {dialog, _ ->
                    dialog.dismiss()
                }
                .setPositiveButton("Оформить") {dialog, _ ->
                    dialog.dismiss()
                    viewModel.orderButton()
                    Snackbar.make(view, "Заказ оформлен!", Snackbar.LENGTH_SHORT).show()
                }.show()
        }

        viewModel.cart.observe(viewLifecycleOwner) {
            adapter.updateData(it)
        }

        viewModel.loadCart()
    }

}