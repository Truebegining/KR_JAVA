package com.example.kr_kotlin.ui.catalog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SearchView
import androidx.navigation.fragment.findNavController
import com.example.kr_kotlin.R
import com.example.kr_kotlin.databinding.FragmentCartBinding
import com.example.kr_kotlin.databinding.FragmentCatalogBinding
import com.example.kr_kotlin.ui.sub_catalog.SubCatalogFragmentDirections

class CatalogFragment : Fragment() {

    private var __binding : FragmentCatalogBinding? = null
    private val mBinding get() = __binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        __binding = FragmentCatalogBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.buttonSofas.setOnClickListener {
            val action = CatalogFragmentDirections
                .actionCatalogFragmentToSubCatalogFragment("диван")
            findNavController().navigate(action)
        }
        mBinding.buttonWardrobes.setOnClickListener {
            val action = CatalogFragmentDirections
                .actionCatalogFragmentToSubCatalogFragment("шкаф")
            findNavController().navigate(action)
        }
        mBinding.buttonBeds.setOnClickListener {
            val action = CatalogFragmentDirections
                .actionCatalogFragmentToSubCatalogFragment("кровать")
            findNavController().navigate(action)
        }

        mBinding.icUser.setOnClickListener {
            findNavController().navigate(R.id.action_catalogFragment_to_profileFragment)
        }
    }
}