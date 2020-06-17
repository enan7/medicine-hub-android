package com.example.medic;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyHolder extends RecyclerView.ViewHolder {

    CircleImageView mImageView;
    TextView mTitle, mPrice;


    public MyHolder(@NonNull View itemView) {
        super(itemView);

        this.mImageView = itemView.findViewById(R.id.item_image);
        this.mTitle = itemView.findViewById(R.id.item_title);
        this.mPrice = itemView.findViewById(R.id.item_newprice);
    }
}
