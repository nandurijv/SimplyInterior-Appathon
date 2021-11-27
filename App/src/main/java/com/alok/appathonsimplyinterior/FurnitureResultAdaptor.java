package com.alok.appathonsimplyinterior;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FurnitureResultAdaptor extends RecyclerView.Adapter<FurnitureResultAdaptor.FurnitureHolder> {

    LayoutInflater inflater;
    List<Furniture> furnitures;
    Context context;


    public FurnitureResultAdaptor(Context ctx, List<Furniture> furnitures) {
        this.inflater = LayoutInflater.from(ctx);
        this.furnitures = furnitures;
        this.context = ctx;
    }

    @NonNull
    @Override
    public FurnitureHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_layout_item, parent, false);
        return new FurnitureHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FurnitureHolder holder, int position) {
        holder.name.setText(furnitures.get(position).getName());
        holder.price.setText("₹"+furnitures.get(position).getPrice());
        Glide.with(holder.productImage.getContext()).load(furnitures.get(position).getImageUrl()).into(holder.productImage);
        holder.buyNow.setOnClickListener(view -> {

            Log.i("button check", "onBindViewHolder: " + furnitures.get(position).getProductUrl());
            String url = furnitures.get(position).getProductUrl();

            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return furnitures.size();
    }

    public class FurnitureHolder extends RecyclerView.ViewHolder {
        TextView name, price, productUrl;
        ImageView productImage;
        Button buyNow;

        public FurnitureHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.itemName);
            price = itemView.findViewById(R.id.price);
            productImage = itemView.findViewById(R.id.productImage);
            buyNow = itemView.findViewById(R.id.buyNow);
        }
    }
}