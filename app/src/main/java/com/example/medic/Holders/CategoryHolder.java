package com.example.medic.Holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryHolder extends RecyclerView.ViewHolder {

    public CircleImageView getCatImageView;
    private ImageView catImageView;
    private TextView catTitle;


    public ImageView getCatImageView() {
        return catImageView;
    }

    public void setCatImageView(ImageView catImageView) {
        this.catImageView = catImageView;
    }

    public TextView getCatTitle() {
        return catTitle;
    }

    public void setCatTitle(TextView catTitle) {
        this.catTitle = catTitle;
    }

    public CategoryHolder(@NonNull View categoryView) {
        super(categoryView);

        this.catImageView = categoryView.findViewById(R.id.cat_img);
        this.catTitle = categoryView.findViewById(R.id.cat_name);

    }
}
