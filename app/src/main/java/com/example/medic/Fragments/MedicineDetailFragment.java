package com.example.medic.Fragments;

import android.graphics.Paint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.medic.Activity.CustomiseToolbar;
import com.example.medic.Activity.Home;
import com.example.medic.R;
import com.example.medic.Responses.MedicineResponse;

import java.io.Serializable;


public class MedicineDetailFragment extends Fragment {

    private TextView medicineName, medicinePrice, medicineDiscount, medicineOldprice,
            medicineDescription, medicineQuantity, medicineUnit, medicineManufacturer;
    private ImageView medicineImage;
    private LinearLayout priceDiscount;
    private Button AddToCart;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_medicine_detail, container, false);

        /*MedicineResponse medicines = (MedicineResponse) getIntent().getSerializableExtra("Med");*/
        MedicineResponse medicines = (MedicineResponse) getArguments().getSerializable("Med");

        medicineName = (TextView) view.findViewById(R.id.detail_title);
        medicineDescription = (TextView) view.findViewById(R.id.detail_description);
        medicineDiscount =  (TextView) view.findViewById(R.id.detail_dicount);
        medicineOldprice = (TextView) view.findViewById(R.id.detail_oldprice);
        medicinePrice = (TextView) view.findViewById(R.id.detail_newprice);
        medicineImage = (ImageView) view.findViewById(R.id.detail_image);
        medicineQuantity = (TextView) view.findViewById(R.id.detail_qty);
        medicineUnit = (TextView) view.findViewById(R.id.detail_unit);
        medicineManufacturer = (TextView) view.findViewById(R.id.detail_manufacture);
        priceDiscount = (LinearLayout) view.findViewById(R.id.price_discount);
        AddToCart = (Button) view.findViewById(R.id.add_to_cart);

        medicineName.setText(medicines.getMedicineName());
        medicinePrice.setText(medicines.getCalculatedPrice().toString()+" Rs");
        medicineDiscount.setText("Discount: "+(String.valueOf(medicines.getDiscount())+"%"));
        medicineDescription.setText("Description: "+(medicines.getDescription()));
        medicineImage.setImageBitmap(medicines.getImage());
        medicineOldprice.setText("Old Price: "+(medicines.getPrice().toString()));
        medicineOldprice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        medicineQuantity.setText(("("+medicines.getQuantity().toString()+")"));
        medicineUnit.setText(medicines.getUnit());
        medicineManufacturer.setText("By ("+(medicines.getManufacturer())+")");



        AddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doIncrease();
            }
        });



        if (String.valueOf(medicines.getDiscount()) .equals("0") )
        {
            priceDiscount.setVisibility(View.GONE);
        }
        return view;
    }

    private void doIncrease() {
        CustomiseToolbar toolbar = new CustomiseToolbar();
        toolbar.increaseCount();
        Home toolbar1 = new Home();
        toolbar1.increaseCount();

    }
}