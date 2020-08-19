package com.example.medic.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.Holders.CartHolder;
import com.example.medic.R;
import com.example.medic.Responses.CartDetailDTO;

import java.util.ArrayList;

public class CartDetailAdapter extends RecyclerView.Adapter<CartHolder> {


    Context c;
    byte[] decodedString;
    Bitmap decodedByte;
    ArrayList<CartDetailDTO> cart;
    Dialog myDialog;

    public CartDetailAdapter(Context c, ArrayList<CartDetailDTO> cart) {
        this.c = c;
        this.cart = cart;
    }

    @NonNull
    @Override
    public CartHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_medicine_layout, null);

        myDialog = new Dialog(view.getContext());
        myDialog.setContentView(R.layout.cart_item_popup);


        return new CartHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull CartHolder cartHolder, final int i) {

        cartHolder.getMedicineName().setText(cart.get(i).getMedicineName());

        cartHolder.getMedicineQty().setText(String.valueOf(cart.get(i).getMedicineQuantity()));
        cartHolder.getMedicineUnit().setText(cart.get(i).getMedicineUnit());

        cartHolder.getNewPrice().setText("Rs. " + String.valueOf(cart.get(i).getMedicinePrice()));
        cartHolder.getQtyButton().setNumber(String.valueOf(cart.get(i).getItemQuantity()));

        if (cart.get(i).getMedicineIcon() != null) {

            cartHolder.getMedicineImage().setImageBitmap(cart.get(i).getMedicineIcon());
        }


        cartHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView crossButton = (TextView) myDialog.findViewById(R.id.close_popup);
                crossButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myDialog.dismiss();
                    }
                });

                ImageView cartImage = (ImageView) myDialog.findViewById(R.id.cart_detail_imageView);
                cartImage.setImageBitmap(cart.get(i).getMedicineIcon());

                TextView cartName = (TextView) myDialog.findViewById(R.id.cart_detail_name);
                cartName.setText(cart.get(i).getMedicineName());

                TextView cartItemQuantity = (TextView) myDialog.findViewById(R.id.cart_detail_quantity);
                cartItemQuantity.setText(String.valueOf(cart.get(i).getMedicineQuantity()));

                TextView cartUnit = (TextView) myDialog.findViewById(R.id.cart_detail_unit);
                cartUnit.setText(cart.get(i).getMedicineUnit());

                TextView cartPrice = (TextView) myDialog.findViewById(R.id.cart_detail_price);
                cartPrice.setText(String.valueOf("Rs. " + cart.get(i).getMedicinePrice()));


                myDialog.show();
            }
        });

    }






    @Override
    public int getItemCount() {
        return cart.size();
    }
}
