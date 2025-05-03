package com.example.kr_kotlin.ui.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kr_kotlin.R
import com.example.kr_kotlin.databinding.FragmentCatalogBinding
import com.example.kr_kotlin.databinding.FragmentFavoritesBinding


class FavoritesFragment : Fragment() {

    private var __binding : FragmentFavoritesBinding? = null
    private val mBinding get() = __binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        __binding = FragmentFavoritesBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }





}