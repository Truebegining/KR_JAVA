package com.example.kr_kotlin.ui.cart_java;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kr_kotlin.databinding.FragmentCartBinding;
import com.example.kr_kotlin.ui.sub_catalog_java.ProductViewModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;


import java.util.List;

public class CartFragment extends Fragment {

    private FragmentCartBinding mBinding;

    private RecyclerView recyclerView;
    private CartAdapter adapter;
    private CartViewModel cartViewModel;
    private ProductViewModel productViewModel;
    private Button orderButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentCartBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        adapter = new CartAdapter(List.of(), product -> cartViewModel.delFromCart(product),
                product -> {
                    NavController navController = NavHostFragment.findNavController(CartFragment.this);
                    NavDirections action = CartFragmentDirections.actionCartFragmentToProductCardFragment(product);
                    navController.navigate(action);
                });

        recyclerView = mBinding.recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);

        orderButton = mBinding.orderButton;

        orderButton.setOnClickListener(v -> new MaterialAlertDialogBuilder(requireContext())
                .setTitle("Подтвердите заказ")
                .setMessage("Вы уверены, что хотите оформить заказ?")
                .setNegativeButton("Отмена", (dialog, which) -> dialog.dismiss())
                .setPositiveButton("Оформить", (dialog, which) -> {
                    dialog.dismiss();
                    cartViewModel.orderButton();
                    Snackbar.make(view, "Заказ оформлен!", Snackbar.LENGTH_SHORT).show();
                }).show());

        cartViewModel.cart.observe(getViewLifecycleOwner(), products -> adapter.updateData(products));
        cartViewModel.loadCart();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}