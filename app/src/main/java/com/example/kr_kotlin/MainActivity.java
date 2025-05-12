package com.example.kr_kotlin;

import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.kr_kotlin.R;
import com.example.kr_kotlin.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import androidx.core.graphics.Insets;
import androidx.core.view.WindowCompat;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EdgeToEdge.enable(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0);
            return insets;
        });

        NavHostFragment navHostFragment = (NavHostFragment)
                getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment == null) return;
        NavController navController = navHostFragment.getNavController();

        NavigationUI.setupWithNavController(binding.bottomBar, navController);

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            int destId = destination.getId();
            if (destId == R.id.authFragment || destId == R.id.registrFragment) {
                binding.bottomBar.setVisibility(View.GONE);
            } else {
                binding.bottomBar.setVisibility(View.VISIBLE);
            }
        });

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            navController.navigate(R.id.action_authFragment_to_catalogFragment);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}

