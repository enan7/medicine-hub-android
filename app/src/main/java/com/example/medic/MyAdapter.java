package com.example.medic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.Model.Items;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    Context c;

    ArrayList<Items> items;

    public MyAdapter(Context c, ArrayList<Items> items) {
        this.c = c;
        this.items = items;
    }



    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout, null);
        return new MyHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {

        myHolder.mTitle.setText(items.get(i).getTitle());
        myHolder.mPrice.setText(items.get(i).getPrice());
        myHolder.mImageView.setImageResource(items.get(i).getImg());

    }


    @Override
    public int getItemCount() {
        return items.size();
    }
}
