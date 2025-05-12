package com.example.kr_kotlin.ui.sub_catalog_java;


import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    private String article;
    private String imageUrl;
    private String dimensions;
    private String price;
    private String name;
    private String id;

    public Product() {
    }

    public Product(String article, String imageUrl, String dimensions, String price, String name, String id) {
        this.article = article;
        this.imageUrl = imageUrl;
        this.dimensions = dimensions;
        this.price = price;
        this.name = name;
        this.id = id;
    }

    protected Product(Parcel in) {
        article = in.readString();
        imageUrl = in.readString();
        dimensions = in.readString();
        price = in.readString();
        name = in.readString();
        id = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getArticle() {
        return article;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDimensions() {
        return dimensions;
    }

    public String getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String string){
        id = string;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(article);
        dest.writeString(imageUrl);
        dest.writeString(dimensions);
        dest.writeString(price);
        dest.writeString(name);
        dest.writeString(id);
    }
}

