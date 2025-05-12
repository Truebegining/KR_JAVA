package com.example.kr_kotlin.ui.registration_java;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.kr_kotlin.R;
import com.example.kr_kotlin.databinding.FragmentRegistrBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.HashMap;
import java.util.Map;

public class RegistrFragment extends Fragment {

    private FragmentRegistrBinding binding;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        binding = FragmentRegistrBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonCreateAcc.setOnClickListener(v -> {
            String email = binding.emailEt.getText().toString().trim();
            String password = binding.passwordEt.getText().toString().trim();
            String repassword = binding.repasswordEt.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty() || repassword.isEmpty()) {
                Toast.makeText(requireContext(),
                        "Поля не могут быть пустыми",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(repassword)) {
                Toast.makeText(requireContext(),
                        "Пароли должны быть одинаковыми",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            saveEmailToDatabase(email, view);
                        } else {
                            Toast.makeText(requireContext(),
                                    "Ошибка регистрации: " +
                                            task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }

    private void saveEmailToDatabase(String email, View view) {
        String userId = FirebaseAuth.getInstance().getCurrentUser() != null
                ? FirebaseAuth.getInstance().getCurrentUser().getUid()
                : null;

        if (userId == null) {
            Toast.makeText(requireContext(),
                    "Ошибка: пользователь не найден",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> userData = new HashMap<>();
        userData.put("email", email);
        userData.put("createdAt", ServerValue.TIMESTAMP);

        FirebaseDatabase.getInstance().getReference()
                .child("Users")
                .child(userId)
                .setValue(userData)
                .addOnCompleteListener(dbTask -> {
                    if (dbTask.isSuccessful()) {
                        Navigation.findNavController(view)
                                .navigate(R.id.action_registrFragment_to_authFragment);
                    } else {
                        Toast.makeText(requireContext(),
                                "Ошибка сохранения данных: " +
                                        dbTask.getException().getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
