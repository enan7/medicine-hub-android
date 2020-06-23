package com.example.medic.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.Holders.CategoryHolder;
import com.example.medic.Model.Items;
import com.example.medic.MyHolder;
import com.example.medic.R;
import com.example.medic.Responses.CategoryListResponse;
import com.example.medic.Responses.CategoryResponse;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryHolder> {

    Context c;

    ArrayList<CategoryResponse> categories;

    public CategoryAdapter(Context c, ArrayList<CategoryResponse> categories) {
        this.c = c;
        this.categories = categories;
    }


    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_layout, null);
        return new CategoryHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CategoryHolder categoryHolder, int i) {

        categoryHolder.getCatTitle().setText(categories.get(i).getCategoryName());

        Bitmap decodedByte = BitmapFactory.decodeByteArray(categories.get(i).getCategoryIcon(), 0, categories.get(i).getCategoryIcon().length);
        categoryHolder.getCatImageView().setImageBitmap(decodedByte);

    }


    @Override
    public int getItemCount() {
        return categories.size();
    }
}
