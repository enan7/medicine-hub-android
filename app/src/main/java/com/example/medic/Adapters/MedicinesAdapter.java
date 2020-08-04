package com.example.medic.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.Fragments.MedicineDetailFragment;
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
        medicineHolder.getMedicinePrice().setText(medicines.get(i).getCalculatedPrice().toString() + " Rs");
        medicineHolder.getMedicineOldprice().setText(medicines.get(i).getPrice().toString());
        medicineHolder.getMedicineOldprice().setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        medicineHolder.getMedicineDicount().setText(String.valueOf(medicines.get(i).getDiscount()) + "% off");
        medicineHolder.getMedicineQuantity().setText(" " + "(" + (medicines.get(i).getQuantity().toString()) + ")");
        medicineHolder.getMedicineUnit().setText(medicines.get(i).getUnit());

        if (String.valueOf(medicines.get(i).getDiscount()).equals("0")) {
            medicineHolder.getMedicineDicount().setVisibility(View.GONE);
            medicineHolder.getMedicineOldprice().setVisibility(View.GONE);
        }


        if (medicines.get(i).getImage() != null) {

            medicineHolder.getMedicineImage().setImageBitmap(medicines.get(i).getImage());
        }

        medicineHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Bundle bundle=new Bundle();
                bundle.putSerializable("Med", medicines.get(i));

                AppCompatActivity activity = (AppCompatActivity)v.getContext();
                MedicineDetailFragment medicineDetailFragment = new MedicineDetailFragment();
                medicineDetailFragment.setArguments(bundle);
                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, medicineDetailFragment)
                        .addToBackStack(null)
                        .commit();

              /*  Intent intent = new Intent(c, CustomiseToolbar.class);
                intent.putExtra("Med", medicines.get(i));
                intent.putExtra("CatID","Medicine");
                c.startActivity(intent);*/


            }
        });



    }

    @Override
    public int getItemCount() {
        return medicines.size();
    }


}
