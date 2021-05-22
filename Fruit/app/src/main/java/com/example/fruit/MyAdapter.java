package com.example.fruit;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fruit.databinding.ItemFruitBinding;

import java.util.List;

public class MyAdaper extends RecyclerView.Adapter<MyAdaper.ViewHolder> {
    private List<Fruit> fruits;

    @NonNull
    @Override
    public MyAdaper.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFruitBinding binding = ItemFruitBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Fruit fruit = fruits.get(position);
        holder.binding.tvName.setText(fruit.getName());
        holder.binding.ivFruit.setImageResource(fruit.getImageId());
    }

    @Override
    public int getItemCount() {
        return fruits.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemFruitBinding binding;

        public ViewHolder(@NonNull ItemFruitBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}