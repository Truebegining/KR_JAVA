package com.example.kr_kotlin.ui.productCard_java;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.kr_kotlin.databinding.FragmentProductCardBinding;
import com.example.kr_kotlin.ui.cart_java.CartViewModel;
import com.example.kr_kotlin.ui.sub_catalog_java.Product;
import com.example.kr_kotlin.ui.sub_catalog_java.ProductViewModel;

//import com.example.kr_kotlin.ui.product_cardf_java.ProductCardfFragmentArgs;

public class ProductCardFragment extends Fragment {

    private FragmentProductCardBinding binding;
    private CartViewModel cartViewModel;
    private ProductViewModel productViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProductCardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cartViewModel = new ViewModelProvider(requireActivity()).get(CartViewModel.class);

        ProductCardFragmentArgs args = ProductCardFragmentArgs.fromBundle(getArguments());
        Product product = args.getProduct();

        // ВНИМАНИЕ: ViewModel нельзя передавать через SafeArgs, только как синглтон или через DI
        // Здесь безопасно инициализируем заново
        productViewModel = new ViewModelProvider(requireActivity()).get(ProductViewModel.class);

        binding.nameTextView.setText(product.getName());
        binding.dimensionsTextView.setText(product.getDimensions());
        binding.priceTextView.setText("Цена: " + product.getPrice());

        Glide.with(this)
                .load(product.getImageUrl())
                .into(binding.productImageView);

        // Сначала нужно подписаться на LiveData
        productViewModel.getFavorites().observe(getViewLifecycleOwner(), favorites -> {
            boolean isFavorite = false;
            for (Product p : favorites) {
                if (p.getId().equals(product.getId())) {
                    isFavorite = true;
                    break;
                }
            }
            binding.favButton.setChecked(isFavorite);
        });

        binding.buyButton.setOnClickListener(v -> cartViewModel.buyButton(product));

        binding.favButton.setOnClickListener(v -> productViewModel.toggleFavorite(product));

        binding.backButton.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(requireView());
            navController.navigateUp();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
