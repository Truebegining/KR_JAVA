package com.example.kr_kotlin.ui.catalog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SearchView
import com.example.kr_kotlin.R
import com.example.kr_kotlin.databinding.FragmentCartBinding
import com.example.kr_kotlin.databinding.FragmentCatalogBinding

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
}