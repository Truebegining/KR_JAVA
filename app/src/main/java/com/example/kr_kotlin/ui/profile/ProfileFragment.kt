package com.example.kr_kotlin.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.privacysandbox.ads.adservices.adid.AdId
import com.example.kr_kotlin.R
import com.example.kr_kotlin.databinding.FragmentCatalogBinding
import com.example.kr_kotlin.databinding.FragmentProfileBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class ProfileFragment : Fragment() {

    private var __binding : FragmentProfileBinding? = null
    private val mBinding get() = __binding!!

    lateinit var emailTv : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        __binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userId = FirebaseAuth.getInstance().currentUser?.uid
        emailTv = mBinding.emailTv

        if (userId != null) {
            getUserData(userId)
        }
        else {
            Toast.makeText(requireContext(), "Пользователь не авторизован", Toast.LENGTH_SHORT)
                .show()
        }

        mBinding.buttonLogOut.setOnClickListener {
            Firebase.auth.signOut()
            findNavController().navigate(R.id.action_profileFragment_to_authFragment)
        }
    }

    private fun getUserData(userId: String) {
        val database: DatabaseReference = FirebaseDatabase.getInstance().reference
        val userRef = database.child("Users").child(userId)

        userRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val emailBd = snapshot.child("email").getValue(String::class.java)

                emailTv.text = emailBd?: "Ошибка: не найдено email"
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "Ошибка: ${error.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}