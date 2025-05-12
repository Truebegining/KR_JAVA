package com.example.kr_kotlin.ui.sub_catalog_java;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.fragment.NavHostFragment.*;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kr_kotlin.R;
import com.example.kr_kotlin.databinding.FragmentSubCatalogBinding;
import com.example.kr_kotlin.ui.cart_java.CartViewModel;
import com.example.kr_kotlin.ui.sub_catalog_java.ProductAdapter;
import com.example.kr_kotlin.ui.sub_catalog_java.ProductViewModel;

import java.util.Collections;
import java.util.List;

public class SubCatalogFragment extends Fragment {

    private FragmentSubCatalogBinding binding;
    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private ProductViewModel productViewModel;
    private CartViewModel cartViewModel;
    private String category;
    private String search;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSubCatalogBinding.inflate(inflater, container, false);

        // Получаем аргументы из safe args
        Bundle args = getArguments();
        if (args != null) {
            SubCatalogFragmentArgs safeArgs = SubCatalogFragmentArgs.fromBundle(args);
            category = safeArgs.getCategory();
            search = safeArgs.getSearch();
        }

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ViewModel’и
        productViewModel = new ViewModelProvider(requireActivity()).get(ProductViewModel.class);
        cartViewModel = new ViewModelProvider(requireActivity()).get(CartViewModel.class);

        // Адаптер с коллбэками (ИСПРАВЛЕННЫЕ ИНТЕРФЕЙСЫ И МЕТОДЫ)
        adapter = new ProductAdapter(
                /* initialData = */ Collections.emptyList(),
                /* initialFavs = */ Collections.emptyList(),
                // Используем правильные интерфейсы и методы:
                new ProductAdapter.OnFavoriteClick() {
                    @Override
                    public void onClick(Product product) {
                        productViewModel.toggleFavorite(product);
                    }
                },
                new ProductAdapter.OnBuyButtonClick() {
                    @Override
                    public void onClick(Product product) {
                        cartViewModel.buyButton(product);
                    }
                },
                new ProductAdapter.OnItemClick() {
                    @Override
                    public void onClick(Product product) {
                        // Навигация к карточке товара
                        NavDirections action =
                                SubCatalogFragmentDirections
                                        .actionSubCatalogFragmentToProductCardFragment(product);
                        NavHostFragment.findNavController(SubCatalogFragment.this)
                                .navigate(action);
                    }
                }
        );

        // RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        recyclerView.setAdapter(adapter);

        // Наблюдаем за живыми данными
        productViewModel.getProducts().observe(getViewLifecycleOwner(), newData -> {
            adapter.updateData(newData);
        });
        productViewModel.getFavorites().observe(getViewLifecycleOwner(), favs -> {
            adapter.updateFavData(favs);
        });

        // Кнопка «назад»
        binding.backButton.setOnClickListener(v ->
                NavHostFragment.findNavController(SubCatalogFragment.this).navigateUp()
        );

        // Загрузка данных
        productViewModel.fetchProducts(category, search);
        productViewModel.loadFavorites();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}