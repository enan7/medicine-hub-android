package com.example.medic.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.medic.R;
import com.example.medic.Responses.MedicineResponse;

import java.io.Serializable;

public class ItemDetail extends AppCompatActivity {

    private TextView medicineName, medicinePrice, medicineDiscount, medicineOldprice, medicineDescription;
    private ImageView medicineImage;

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

        medicineName.setText(medicines.getMedicineName());
        medicinePrice.setText(medicines.getCalculatedPrice().toString()+" Rs");
        medicineDiscount.setText(String.valueOf(medicines.getDiscount())+"% off");
        medicineDescription.setText(medicines.getDescription());
        medicineImage.setImageBitmap(medicines.getImage());
        medicineOldprice.setText(medicines.getPrice().toString());
        medicineOldprice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }
}
