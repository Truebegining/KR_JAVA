package com.example.kr_kotlin.ui.sub_catalog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.kr_kotlin.R

class ProductAdapter(private var productList: List<Product>,
     private var favoritesList: List<Product>,
     private val onFavoriteClick: (Product) -> Unit,
     private val buyButtonClick: (Product) -> Unit,
     private val onItemClick: (Product) -> Unit)
    : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    // Создаём ViewHolder — это оболочка для одного элемента списка
    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val articleTextView: TextView = itemView.findViewById(R.id.articleTextView)
        val productImageView: ImageView = itemView.findViewById(R.id.productImageView)
        val dimensionsTextView: TextView = itemView.findViewById(R.id.dimensionsTextView)
        val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val buyButton: Button = itemView.findViewById(R.id.buyButton)
        val favButton: CheckBox = itemView.findViewById(R.id.favButton)
    }

    // Создаём новый ViewHolder (новую карточку товара)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_catalog, parent, false)
        return ProductViewHolder(view)
    }

    // Заполняем данные в карточке товара
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]

        holder.articleTextView.text = product.article
        holder.dimensionsTextView.text = product.dimensions
        holder.priceTextView.text = "цена: ${product.price}"
        holder.nameTextView.text = product.name

        // Загружаем изображение
        Glide.with(holder.itemView.context)
            .load(product.imageUrl)
            .into(holder.productImageView)

        holder.favButton.isChecked = favoritesList.any{it.id == product.id}

        holder.favButton.setOnClickListener {
            onFavoriteClick(product)
        }

        holder.buyButton.setOnClickListener {
            buyButtonClick(product)
        }

        holder.itemView.setOnClickListener {
            onItemClick(product)
        }
    }

    // Сколько всего товаров
    override fun getItemCount(): Int = productList.size

    fun updateData (newList: List<Product>) {
        productList = newList
        notifyDataSetChanged()
    }
    fun updateFavData (newList: List<Product>) {
        favoritesList = newList
        notifyDataSetChanged()
    }
}
