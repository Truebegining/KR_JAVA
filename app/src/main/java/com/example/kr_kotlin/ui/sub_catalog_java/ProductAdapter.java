package com.example.kr_kotlin.ui.sub_catalog_java;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kr_kotlin.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    public interface OnFavoriteClick {
        void onClick(Product product);
    }

    public interface OnBuyButtonClick {
        void onClick(Product product);
    }

    public interface OnItemClick {
        void onClick(Product product);
    }

    private List<Product> productList;
    private List<Product> favoritesList;
    private final OnFavoriteClick onFavoriteClick;
    private final OnBuyButtonClick buyButtonClick;
    private final OnItemClick onItemClick;

    public ProductAdapter(List<Product> productList, List<Product> favoritesList,
                          OnFavoriteClick onFavoriteClick,
                          OnBuyButtonClick buyButtonClick,
                          OnItemClick onItemClick) {
        this.productList = productList;
        this.favoritesList = favoritesList;
        this.onFavoriteClick = onFavoriteClick;
        this.buyButtonClick = buyButtonClick;
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_catalog, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.articleTextView.setText(product.getArticle());
        holder.dimensionsTextView.setText(product.getDimensions());
        holder.priceTextView.setText("цена: " + product.getPrice());
        holder.nameTextView.setText(product.getName());

        Glide.with(holder.itemView.getContext())
                .load(product.getImageUrl())
                .into(holder.productImageView);

        boolean isFavorite = false;
        for (Product p : favoritesList) {
            if (p.getId().equals(product.getId())) {
                isFavorite = true;
                break;
            }
        }
        holder.favButton.setChecked(isFavorite);

        holder.favButton.setOnClickListener(v -> onFavoriteClick.onClick(product));
        holder.buyButton.setOnClickListener(v -> buyButtonClick.onClick(product));
        holder.itemView.setOnClickListener(v -> onItemClick.onClick(product));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void updateData(List<Product> newList) {
        productList = newList;
        notifyDataSetChanged();
    }

    public void updateFavData(List<Product> newList) {
        favoritesList = newList;
        notifyDataSetChanged();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView articleTextView;
        ImageView productImageView;
        TextView dimensionsTextView;
        TextView priceTextView;
        TextView nameTextView;
        Button buyButton;
        CheckBox favButton;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            articleTextView = itemView.findViewById(R.id.articleTextView);
            productImageView = itemView.findViewById(R.id.productImageView);
            dimensionsTextView = itemView.findViewById(R.id.dimensionsTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            buyButton = itemView.findViewById(R.id.buyButton);
            favButton = itemView.findViewById(R.id.favButton);
        }
    }
}
