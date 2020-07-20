package com.example.medic.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.Activity.Home;
import com.example.medic.Activity.Medicine;
import com.example.medic.Holders.CategoryHolder;
import com.example.medic.R;
import com.example.medic.Responses.CategoryListResponse;
import com.example.medic.Responses.CategoryResponse;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryHolder> {

    Context c;
    byte[] decodedString;
    Bitmap decodedByte;
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
    public void onBindViewHolder(@NonNull CategoryHolder categoryHolder, final int i) {

        categoryHolder.getCatTitle().setText(categories.get(i).getCategoryName());


        if (categories.get(i).getCategoryIcon() != null) {
            byte[] decodedImageUri = Base64.decode(categories.get(i).getCategoryIcon(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedImageUri, 0, decodedImageUri.length);
/*
            categoryHolder.getCatImageView().setColorFilter(ContextCompat.getColor(c, R.color.red_color), android.graphics.PorterDuff.Mode.MULTIPLY);
*/

            categoryHolder.getCatImageView().setImageBitmap(decodedByte);
        }

        categoryHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c, Medicine.class);
                intent.putExtra("CatID",categories.get(i).getId());
                intent.putExtra("ClickID","Category");
                c.startActivity(intent);
/*
                ((Home)c).finish();
*/



            }
        });

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }


}
