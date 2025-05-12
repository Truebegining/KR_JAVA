package com.example.kr_kotlin.ui.favorites_java;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.kr_kotlin.databinding.FragmentFavoritesBinding;
import com.example.kr_kotlin.ui.cart_java.CartViewModel;
import com.example.kr_kotlin.ui.sub_catalog_java.Product;
import com.example.kr_kotlin.ui.sub_catalog_java.ProductAdapter;
import com.example.kr_kotlin.ui.sub_catalog_java.ProductViewModel;
import com.example.kr_kotlin.ui.sub_catalog_java.SubCatalogFragmentDirections;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {

    private FragmentFavoritesBinding binding;

    private ProductViewModel productViewModel;
    private CartViewModel cartViewModel;
    private ProductAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);

        adapter = new ProductAdapter(
                new ArrayList<>(), // пустой список для productList
                new ArrayList<>(), // пустой список для favoritesList
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
                        NavController navController = Navigation.findNavController(requireView());
                        NavDirections action = FavoritesFragmentDirections.actionFavoritesFragmentToProductCardFragment(product);
                        navController.navigate(action);
                    }
                }
        );

        binding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.recyclerView.setAdapter(adapter);

        productViewModel.getFavorites().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                adapter.updateData(products);
                adapter.updateFavData(products);
            }
        });

        productViewModel.loadFavorites();
    }
}