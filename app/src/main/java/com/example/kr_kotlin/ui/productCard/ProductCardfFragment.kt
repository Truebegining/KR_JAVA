package com.example.kr_kotlin.ui.productCard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.kr_kotlin.R
import com.example.kr_kotlin.databinding.FragmentCatalogBinding
import com.example.kr_kotlin.databinding.FragmentProductCardfBinding


class ProductCardfFragment : Fragment() {


    private var __binding : FragmentProductCardfBinding? = null
    private val mBinding get() = __binding!!
    private val args : ProductCardfFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        __binding = FragmentProductCardfBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val product = args.product
        mBinding.nameTextView.text = product.name
        mBinding.dimensionsTextView.text = product.dimensions
        mBinding.priceTextView.text = "Цена: ${product.price}"
        Glide.with(this)
            .load(product.imageUrl)
            .into(mBinding.productImageView)

    }

}