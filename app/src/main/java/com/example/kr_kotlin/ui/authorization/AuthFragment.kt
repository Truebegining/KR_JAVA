package com.example.kr_kotlin.ui.authorization

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.kr_kotlin.R
import com.example.kr_kotlin.databinding.FragmentAuthBinding
import com.example.kr_kotlin.databinding.FragmentRegistrBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth

class AuthFragment : Fragment() {
    private var __binding : FragmentAuthBinding? = null
    private val mBinding get() = __binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        __binding = FragmentAuthBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val email = mBinding.emailEt.text.toString().trim()
//        val password = mBinding.passwordEt.text.toString().trim()

//

        mBinding.buttonSignIn.setOnClickListener {
            val email = mBinding.emailEt.text.toString().trim()
            val password = mBinding.passwordEt.text.toString().trim()
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(),"Поля не могут быть пустыми", Toast.LENGTH_SHORT)
                    .show()
            }
            else {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener{ task ->
                        if (task.isSuccessful) {
                            findNavController().navigate(R.id.action_authFragment_to_catalogFragment)
                        }
                        else {
                            Toast.makeText(requireContext(), "Авторизация не удалась: " +
                                    "${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

        mBinding.buttonCreateAcc.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_registrFragment)
        }


    }
}