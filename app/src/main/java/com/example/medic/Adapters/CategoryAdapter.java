package com.example.medic.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
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

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryHolder> {

    Context c;
    byte[] decodedString;
    Bitmap decodedByte;
    ArrayList<CategoryResponse> categories;

    public CategoryAdapter(Context c, ArrayList<CategoryResponse> categories) {
        this.c = c;
        this.categories = categories;
        Log.v("ayaz1","ayaz1");

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
        Log.v("ayaz1","ayaz1"+categories.get(i).getCategoryName());




        String base64String = "data:image/png;base64,"+categories.get(i).getCategoryIcon();
        Log.v("ayaz1","ayaz1"+categories.get(i).getCategoryIcon());
        String base64Image = base64String.split(",")[1];byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        categoryHolder.getCatImageView().setImageBitmap(decodedByte);
    }


    @Override
    public int getItemCount() {
        return categories.size();
    }









}
