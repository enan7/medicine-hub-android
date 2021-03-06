package com.example.medic.Holders;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.medic.Activity.SignIn;
import com.example.medic.Activity.SignUp;
import com.example.medic.Activity.VerifyPhoneNo;
import com.example.medic.Api_Interfaces.CartInterface;
import com.example.medic.Api_Interfaces.UserInterface;
import com.example.medic.R;
import com.example.medic.Requests.UpdateCartItemRequest;
import com.example.medic.Responses.RegisterUserResponse;
import com.example.medic.Responses.UpdateCartItemResponse;
import com.example.medic.RetrofitClient.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartHolder extends RecyclerView.ViewHolder {

    private ImageView medicineImage, deleteCart;
    private TextView medicineName, medicineQty, medicineUnit, newPrice ;
    private ElegantNumberButton qtyButton;

    public CartHolder(@NonNull View itemView) {
        super(itemView);

        this.medicineImage = itemView.findViewById(R.id.cart_item_image);
        this.deleteCart = itemView.findViewById(R.id.cart_delete_btn);
        this.medicineName = itemView.findViewById(R.id.cart_item_title);
        this.medicineQty = itemView.findViewById(R.id.cart_item_qty);
        this.medicineUnit = itemView.findViewById(R.id.cart_item_unit);
        this.newPrice = itemView.findViewById(R.id.cart_item_newprice);
        this.qtyButton = itemView.findViewById(R.id.cart_elegant_btn);

    }

    public ImageView getMedicineImage() {
        return medicineImage;
    }

    public void setMedicineImage(ImageView medicineImage) {
        this.medicineImage = medicineImage;
    }

    public ImageView getDeleteCart() {
        return deleteCart;
    }

    public void setDeleteCart(ImageView deleteCart) {
        this.deleteCart = deleteCart;
    }

    public TextView getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(TextView medicineName) {
        this.medicineName = medicineName;
    }

    public TextView getMedicineQty() {
        return medicineQty;
    }

    public void setMedicineQty(TextView medicineQty) {
        this.medicineQty = medicineQty;
    }

    public TextView getMedicineUnit() {
        return medicineUnit;
    }

    public void setMedicineUnit(TextView medicineUnit) {
        this.medicineUnit = medicineUnit;
    }



    public TextView getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(TextView newPrice) {
        this.newPrice = newPrice;
    }

    public ElegantNumberButton getQtyButton() {
        return qtyButton;
    }

    public void setQtyButton(ElegantNumberButton qtyButton) {
        this.qtyButton = qtyButton;
    }

    }