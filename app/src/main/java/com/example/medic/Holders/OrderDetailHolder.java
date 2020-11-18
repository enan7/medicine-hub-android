package com.example.medic.Holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.R;

public class OrderDetailHolder extends RecyclerView.ViewHolder {

    private ImageView medicineImage;
    private TextView medicineName, medicineQuantity, medicinePrice;

    public OrderDetailHolder(@NonNull View itemView) {
        super(itemView);

        medicineImage = itemView.findViewById(R.id.med_image);
        medicineName = itemView.findViewById(R.id.med_name);
        medicineQuantity = itemView.findViewById(R.id.med_qty);
        medicinePrice = itemView.findViewById(R.id.med_price);
    }

    public ImageView getMedicineImage() {
        return medicineImage;
    }

    public void setMedicineImage(ImageView medicineImage) {
        this.medicineImage = medicineImage;
    }

    public TextView getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(TextView medicineName) {
        this.medicineName = medicineName;
    }

    public TextView getMedicineQuantity() {
        return medicineQuantity;
    }

    public void setMedicineQuantity(TextView medicineQuantity) {
        this.medicineQuantity = medicineQuantity;
    }

    public TextView getMedicinePrice() {
        return medicinePrice;
    }

    public void setMedicinePrice(TextView medicinePrice) {
        this.medicinePrice = medicinePrice;
    }
}
