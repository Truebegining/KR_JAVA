package com.example.kr_kotlin.ui.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kr_kotlin.R
import com.example.kr_kotlin.ui.sub_catalog.Product


class CartAdapter(private var productList: List<Product>,
                  private val onDelClick: (Product) -> Unit,
                  private val onItemClick: (Product) -> Unit)
    : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: CartViewHolder,
        position: Int
    ) {
        val product = productList[position]

        holder.articleTextView.text = product.article
        holder.dimensionsTextView.text = product.dimensions
        holder.priceTextView.text = "цена: ${product.price}"
        holder.nameTextView.text = product.name

        Glide.with(holder.itemView.context)
            .load(product.imageUrl)
            .into(holder.productImageView)

        holder.delFromCartButton.setOnClickListener {
            onDelClick(product)
        }

        holder.itemView.setOnClickListener {
            onItemClick(product)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun updateData (newList: List<Product>) {
        productList = newList
        notifyDataSetChanged()
    }

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val articleTextView: TextView = itemView.findViewById(R.id.articleTextView)
        val productImageView: ImageView = itemView.findViewById(R.id.productImageView)
        val dimensionsTextView: TextView = itemView.findViewById(R.id.dimensionsTextView)
        val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val delFromCartButton : ImageButton = itemView.findViewById(R.id.delFromCartButton)
    }

}