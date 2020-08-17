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
import com.example.medic.Holders.CartHolder;
import com.example.medic.R;
import com.example.medic.Responses.CartDetailDTO;

import java.io.Serializable;
import java.util.ArrayList;

public class CartDetailAdapter extends RecyclerView.Adapter<CartHolder> {

    Context c;
    byte[] decodedString;
    Bitmap decodedByte;
    ArrayList<CartDetailDTO> cart;

    public CartDetailAdapter(Context c, ArrayList<CartDetailDTO> cart) {
        this.c = c;
        this.cart = cart;
    }

    @NonNull
    @Override
    public CartHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_medicine_layout, null);
        return new CartHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartHolder cartHolder, final int i) {

        cartHolder.getMedicineName().setText(cart.get(i).getMedicineName());
        cartHolder.getMedicineDiscount().setText("10%");
        cartHolder.getMedicineQty().setText(String.valueOf(cart.get(i).getMedicineQuantity()));
        cartHolder.getMedicineUnit().setText(cart.get(i).getMedicineUnit());
        cartHolder.getOldPrice().setText("20000");
        cartHolder.getOldPrice().setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        cartHolder.getNewPrice().setText(String.valueOf(cart.get(i).getMedicinePrice()));
        cartHolder.getQtyButton().setNumber(String.valueOf(cart.get(i).getItemQuantity()));

        if (cart.get(i).getMedicineIcon() != null) {

            cartHolder.getMedicineImage().setImageBitmap(cart.get(i).getMedicineIcon());
        }


        cartHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Bundle bundle = new Bundle();
                bundle.putSerializable("Med", (Serializable) cart.get(i));

                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                MedicineDetailFragment medicineDetailFragment = new MedicineDetailFragment();
                medicineDetailFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction()
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
        return cart.size();
    }
}
