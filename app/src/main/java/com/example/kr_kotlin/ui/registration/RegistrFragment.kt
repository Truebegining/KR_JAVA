package com.example.kr_kotlin.ui.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.kr_kotlin.R
import com.example.kr_kotlin.databinding.FragmentProfileBinding
import com.example.kr_kotlin.databinding.FragmentRegistrBinding

class RegistrFragment : Fragment() {

    private var __binding : FragmentRegistrBinding? = null
    private val mBinding get() = __binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        __binding = FragmentRegistrBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.buttonCreateAcc.setOnClickListener {
            findNavController().navigate(R.id.action_registrFragment_to_authFragment)
        }
    }
}