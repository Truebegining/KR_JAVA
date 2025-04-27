package com.example.kr_kotlin.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.kr_kotlin.R
import com.example.kr_kotlin.databinding.FragmentCatalogBinding
import com.example.kr_kotlin.databinding.FragmentProfileBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class ProfileFragment : Fragment() {

    private var __binding : FragmentProfileBinding? = null
    private val mBinding get() = __binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        __binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.buttonLogOut.setOnClickListener {
            Firebase.auth.signOut()
            findNavController().navigate(R.id.action_profileFragment_to_authFragment)
        }





//        mBinding.buttonLogOut.setOnClickListener {
//            findNavController().navigate(R.id.action_profileFragment_to_authFragment)
//        }
    }
}