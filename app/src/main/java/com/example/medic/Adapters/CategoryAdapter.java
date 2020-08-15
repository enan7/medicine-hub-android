package com.example.medic.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.Fragments.MedicineFragment;
import com.example.medic.Holders.CategoryHolder;
import com.example.medic.R;
import com.example.medic.Responses.CategoryDTO;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryHolder> {



    Context c;
    byte[] decodedString;
    Bitmap decodedByte;
    ArrayList<CategoryDTO> categories;
    public CategoryAdapter(Context c, ArrayList<CategoryDTO> categories) {
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



                Bundle bundle=new Bundle();
                bundle.putLong("CatID",categories.get(i).getId());
                bundle.putString("ClickID","Category");

                AppCompatActivity activity = (AppCompatActivity)v.getContext();
                MedicineFragment medicineFragment = new MedicineFragment();
                medicineFragment.setArguments(bundle);
                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, medicineFragment)
                        .addToBackStack(null)
                        .commit();




               /* Intent intent = new Intent(c, CustomiseToolbar.class);
                intent.putExtra("CatID",categories.get(i).getId());
                intent.putExtra("ClickID","Category");
                c.startActivity(intent);*/



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
