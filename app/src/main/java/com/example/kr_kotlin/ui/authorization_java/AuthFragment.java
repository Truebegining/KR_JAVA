package com.example.kr_kotlin.ui.authorization_java;

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
import com.example.kr_kotlin.databinding.FragmentAuthBinding;
import com.google.firebase.auth.FirebaseAuth;

public class AuthFragment extends Fragment {

    private FragmentAuthBinding binding;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        binding = FragmentAuthBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSignIn.setOnClickListener(v -> {
            String email = binding.emailEt.getText().toString().trim();
            String password = binding.passwordEt.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(),
                        "Поля не могут быть пустыми",
                        Toast.LENGTH_SHORT).show();
            } else {
                FirebaseAuth.getInstance()
                        .signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Navigation.findNavController(v)
                                        .navigate(R.id.action_authFragment_to_catalogFragment);
                            } else {
                                Toast.makeText(requireContext(),
                                        "Авторизация не удалась: " +
                                                task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        binding.buttonCreateAcc.setOnClickListener(v ->
                Navigation.findNavController(v)
                        .navigate(R.id.action_authFragment_to_registrFragment)
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}