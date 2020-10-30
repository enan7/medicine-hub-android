package com.example.medic.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.medic.Api_Interfaces.CartInterface;
import com.example.medic.Fragments.CartDetailFragment;
import com.example.medic.Fragments.DeleteCartItemDialogFragment;
import com.example.medic.Holders.CartHolder;
import com.example.medic.R;
import com.example.medic.Requests.UpdateCartItemRequest;
import com.example.medic.Responses.CartDetailDTO;
import com.example.medic.Responses.UpdateCartItemResponse;
import com.example.medic.RetrofitClient.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartDetailAdapter extends RecyclerView.Adapter<CartHolder> {



    Context c;
    byte[] decodedString;
    Bitmap decodedByte;
    ArrayList<CartDetailDTO> cart;
    Dialog myDialog;
    RecyclerView cartRecyclerView;
    private CartDetailAdapter mAdapter;
    private RetrofitClient retrofitClient;
    private CartInterface cartInterface;
    RelativeLayout progressBar;

    public CartDetailAdapter(Context c, ArrayList<CartDetailDTO> cart,RecyclerView cartRecyclerView) {
        this.c = c;
        this.cart = cart;
        this.cartRecyclerView = cartRecyclerView;
        mAdapter = this;

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
    public void onBindViewHolder(@NonNull final CartHolder cartHolder, final int i) {



        cartHolder.getDeleteCart().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Long itemId = cart.get(i).getItemId();

                Bundle bundle = new Bundle();
                bundle.putLong("ItemID", itemId);



                DeleteCartItemDialogFragment deleteCartItemDialogFragment = new DeleteCartItemDialogFragment(cart,mAdapter,cartRecyclerView);
                deleteCartItemDialogFragment.setArguments(bundle);
                FragmentManager manager = ((AppCompatActivity)c).getSupportFragmentManager();
                deleteCartItemDialogFragment.show(manager,"MyDialogFragment2");


            }
        });

        cartHolder.getMedicineName().setText(cart.get(i).getMedicineName());

        cartHolder.getMedicineQty().setText(String.valueOf(cart.get(i).getMedicineQuantity()));
        cartHolder.getMedicineUnit().setText(cart.get(i).getMedicineUnit());

        cartHolder.getNewPrice().setText("Rs. " + cart.get(i).getItemPriceWithQuantityAndDiscount());
        cartHolder.getQtyButton().setNumber(String.valueOf(cart.get(i).getItemQuantity()));
        cartHolder.getQtyButton().setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                UpdateCartItemRequest request = new UpdateCartItemRequest();
                String index = view.getNumber();
                request.setItemId(cart.get(i).getItemId());
                request.setQuantity(newValue);
                updateCartItem(request,cartHolder);
            }
        });
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

                TextView cartOldPrice = (TextView) myDialog.findViewById(R.id.cart_detail_oldprice);
                cartOldPrice.setText(String.valueOf(cart.get(i).getItemPriceWithoutQuantityAndDiscount()));
                cartOldPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);

                TextView cartPrice = (TextView) myDialog.findViewById(R.id.cart_detail_price);
                cartPrice.setText("Rs. " + cart.get(i).getItemPriceWithSingleQuantityAndDiscount());

                TextView cartDiscount = (TextView) myDialog.findViewById(R.id.cart_detail_discount);
                cartDiscount.setText(cart.get(i).getDiscount() + "%");

                TextView cartManufacturer = (TextView) myDialog.findViewById(R.id.cart_detail_manufacturer);
                cartManufacturer.setText(cart.get(i).getManufacturer());

                TextView cartDescription = (TextView) myDialog.findViewById(R.id.cart_detail_description);
                cartDescription.setText("Description: " + cart.get(i).getDescription());

                myDialog.show();
            }
        });

    }






    @Override
    public int getItemCount() {
        return cart.size();
    }

    void updateCartItem(UpdateCartItemRequest request, final CartHolder holder) {

        retrofitClient = RetrofitClient.getInstance();
        cartInterface = retrofitClient.getRetrofit().create(CartInterface.class);
        Call<UpdateCartItemResponse> call = cartInterface.updateCartItem(retrofitClient.getJwtToken(),request);

        call.enqueue(new Callback<UpdateCartItemResponse>() {
            @Override
            public void onResponse(Call<UpdateCartItemResponse> call, Response<UpdateCartItemResponse> response) {
                // loadingBar.dismiss();
                UpdateCartItemResponse updateCartItemResponse = response.body();
                //   Toast.makeText(SignUp.this,registerUserResponse.getResponseMessage(),Toast.LENGTH_LONG).show();
                System.out.println(updateCartItemResponse.getResponseCode());


                if(updateCartItemResponse.getResponseCode().equals("00")) {
                    holder.getNewPrice().setText("Rs. " + updateCartItemResponse.getItemPrice());
                    CartDetailFragment.setTotalPrice(updateCartItemResponse.getCartTotalPrice());
                }

            }

            @Override
            public void onFailure(Call<UpdateCartItemResponse> call, Throwable t) {
                // loadingBar.dismiss();
                System.out.println("Failed");
            }
        });
    }

}
