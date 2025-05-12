package com.example.kr_kotlin.ui.catalog_java;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import com.example.kr_kotlin.databinding.FragmentCatalogBinding;

public class CatalogFragment extends Fragment {

    private FragmentCatalogBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCatalogBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSofas.setOnClickListener(v -> {
            CatalogFragmentDirections.ActionCatalogFragmentToSubCatalogFragment action =
                    CatalogFragmentDirections.actionCatalogFragmentToSubCatalogFragment()
                            .setCategory("диван")
                            .setSearch(null);
            NavHostFragment.findNavController(this).navigate(action);
        });

        binding.buttonWardrobes.setOnClickListener(v -> {
            CatalogFragmentDirections.ActionCatalogFragmentToSubCatalogFragment action =
                    CatalogFragmentDirections.actionCatalogFragmentToSubCatalogFragment()
                            .setCategory("шкаф")
                            .setSearch(null);
            NavHostFragment.findNavController(this).navigate(action);
        });

        binding.buttonBeds.setOnClickListener(v -> {
            CatalogFragmentDirections.ActionCatalogFragmentToSubCatalogFragment action =
                    CatalogFragmentDirections.actionCatalogFragmentToSubCatalogFragment()
                            .setCategory("кровать")
                            .setSearch(null);
            NavHostFragment.findNavController(this).navigate(action);
        });

        binding.icUser.setOnClickListener(v ->
                NavHostFragment.findNavController(this)
                        .navigate(com.example.kr_kotlin.R.id.action_catalogFragment_to_profileFragment)
        );

        SearchView searchView = binding.searchView;

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                if (query != null && !query.trim().isEmpty()) {
                    CatalogFragmentDirections.ActionCatalogFragmentToSubCatalogFragment action =
                            CatalogFragmentDirections.actionCatalogFragmentToSubCatalogFragment()
                                    .setCategory(null)
                                    .setSearch(query.trim());
                    NavHostFragment.findNavController(CatalogFragment.this).navigate(action);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
    }
}
