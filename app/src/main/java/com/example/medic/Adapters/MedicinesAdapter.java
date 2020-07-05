package com.example.medic.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.Activity.Medicine;
import com.example.medic.Holders.MedicineHolder;
import com.example.medic.R;
import com.example.medic.Responses.MedicineResponse;

import java.util.ArrayList;

public class MedicinesAdapter extends RecyclerView.Adapter<MedicineHolder> {

    Context c;
    byte[] decodedString;
    Bitmap decodedByte;
    ArrayList<MedicineResponse> medicines;

    public MedicinesAdapter(Context c, ArrayList<MedicineResponse> medicines) {
        this.c = c;
        this.medicines = medicines;
}

    @NonNull
    @Override
    public MedicineHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.medicine_layout, null);
        return new MedicineHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MedicineHolder medicineHolder, final int i) {

        medicineHolder.getMedicineTitle().setText(medicines.get(i).getMedicineName());

        medicineHolder.getMedicinePrice().setText(medicines.get(i).getPrice().toString());
        medicineHolder.getMedicineDicount().setText(medicines.get(i).getPrice().toString());


        if (medicines.get(i).getImage() != null) {
            byte[] decodedImageUri = Base64.decode(medicines.get(i).getImage(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedImageUri, 0, decodedImageUri.length);
            medicineHolder.getMedicineImage().setImageBitmap(decodedByte);
        }

       /* MedicineHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c, Medicine.class);
                intent.putExtra("CatID",medicines.get(i).getId());
                c.startActivity(intent);
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return medicines.size();
    }


}
