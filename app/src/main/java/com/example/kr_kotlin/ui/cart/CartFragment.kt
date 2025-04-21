package com.example.kr_kotlin.ui.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kr_kotlin.R
import com.example.kr_kotlin.databinding.FragmentCartBinding

class CartFragment : Fragment() {

    private var __binding : FragmentCartBinding? = null
    private val mBinding get() = __binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        __binding = FragmentCartBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }
}
///////////////////ОСТАНОВИЛИСЬ НА ТОМ ЧТО ДОБАВИЛИ БИНДИНГИ ВО ВСЕ ФРАГМЕНТЫ. ДАЛЕЕ ВРОДЕ
//ПЕРЕХОДИЛ К NAV BAR