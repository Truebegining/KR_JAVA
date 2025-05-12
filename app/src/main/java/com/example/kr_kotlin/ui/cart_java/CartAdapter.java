package com.example.kr_kotlin.ui.cart_java;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kr_kotlin.R;
import com.example.kr_kotlin.ui.sub_catalog_java.Product;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<Product> productList;
    private final OnDelClickListener onDelClick;
    private final OnItemClickListener onItemClick;

    public interface OnDelClickListener {
        void onDelClick(Product product);
    }

    public interface OnItemClickListener {
        void onItemClick(Product product);
    }

    public CartAdapter(List<Product> productList, OnDelClickListener onDelClick, OnItemClickListener onItemClick) {
        this.productList = productList;
        this.onDelClick = onDelClick;
        this.onItemClick = onItemClick;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.articleTextView.setText(product.getArticle());
        holder.dimensionsTextView.setText(product.getDimensions());
        holder.priceTextView.setText("цена: " + product.getPrice());
        holder.nameTextView.setText(product.getName());

        Glide.with(holder.itemView.getContext())
                .load(product.getImageUrl())
                .into(holder.productImageView);

        holder.delFromCartButton.setOnClickListener(v -> onDelClick.onDelClick(product));

        holder.itemView.setOnClickListener(v -> onItemClick.onItemClick(product));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void updateData(List<Product> newList) {
        productList = newList;
        notifyDataSetChanged();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {

        TextView articleTextView;
        ImageView productImageView;
        TextView dimensionsTextView;
        TextView priceTextView;
        TextView nameTextView;
        ImageButton delFromCartButton;

        public CartViewHolder(View itemView) {
            super(itemView);
            articleTextView = itemView.findViewById(R.id.articleTextView);
            productImageView = itemView.findViewById(R.id.productImageView);
            dimensionsTextView = itemView.findViewById(R.id.dimensionsTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            delFromCartButton = itemView.findViewById(R.id.delFromCartButton);
        }
    }
}
