package com.example.kr_kotlin.ui.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.kr_kotlin.R
import com.example.kr_kotlin.databinding.FragmentProfileBinding
import com.example.kr_kotlin.databinding.FragmentRegistrBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue

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
//        val email = mBinding.emailEt.text.toString()
//        val password = mBinding.passwordEt.text.toString()
//        val repassword = mBinding.repasswordEt.text.toString()

        mBinding.buttonCreateAcc.setOnClickListener {
            val email = mBinding.emailEt.text.toString()
            val password = mBinding.passwordEt.text.toString()
            val repassword = mBinding.repasswordEt.text.toString()

            if (email.isEmpty() || password.isEmpty() || repassword.isEmpty()) {
                Toast.makeText(requireContext(),"Поля не могут быть пустыми", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            else if (password != repassword) {
                Toast.makeText(requireContext(),"Пароли должны быть одинаковыми",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            saveEmailToDataBase(email)
                        }
                        else {
                            Toast.makeText(requireContext(), "Ошибка регистрации: " +
                                    "${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        __binding = null
    }

    private fun saveEmailToDataBase(email: String) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: run {
            Toast.makeText(requireContext(), "Ошибка: пользователь не найден",
                Toast.LENGTH_SHORT).show()
            return
        }
        val userData = hashMapOf("email" to email, "createdAt" to ServerValue.TIMESTAMP)

        FirebaseDatabase.getInstance().reference.child("Users").child(userId)
            .setValue(userData)
        findNavController().navigate(R.id.action_registrFragment_to_authFragment)
    }

}