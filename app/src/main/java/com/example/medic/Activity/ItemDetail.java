package com.example.medic.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.medic.R;
import com.example.medic.Responses.MedicineResponse;

import java.io.Serializable;

public class ItemDetail extends AppCompatActivity {

    private TextView medicineName, medicinePrice, medicineDiscount, medicineOldprice,
                        medicineDescription, medicineQuantity, medicineUnit, medicineManufacturer;
    private ImageView medicineImage;
    private LinearLayout priceDiscount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        MedicineResponse medicines = (MedicineResponse) getIntent().getSerializableExtra("Med");

        medicineName = findViewById(R.id.detail_title);
        medicineDescription = findViewById(R.id.detail_description);
        medicineDiscount = findViewById(R.id.detail_dicount);
        medicineOldprice = findViewById(R.id.detail_oldprice);
        medicinePrice = findViewById(R.id.detail_newprice);
        medicineImage = findViewById(R.id.detail_image);
        medicineQuantity = findViewById(R.id.detail_qty);
        medicineUnit = findViewById(R.id.detail_unit);
        medicineManufacturer = findViewById(R.id.detail_manufacture);
        priceDiscount = findViewById(R.id.price_discount);

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



        if (String.valueOf(medicines.getDiscount()) .equals("0") )
        {
            priceDiscount.setVisibility(View.GONE);
        }

    }
}
