package com.example.medic.Holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.R;

public class MedicineHolder extends RecyclerView.ViewHolder {

    private ImageView medicineImage;
    private TextView medicineTitle, medicinePrice, medicineDicount, medicineOldprice;

    public MedicineHolder(@NonNull View medicineView) {
        super(medicineView);

        this.medicineImage = medicineView.findViewById(R.id.item_image);
        this.medicineTitle = medicineView.findViewById(R.id.item_title);
        this.medicinePrice = medicineView.findViewById(R.id.item_newprice);
        this.medicineDicount = medicineView.findViewById(R.id.item_discount);
        this.medicineOldprice = medicineView.findViewById(R.id.item_oldprice);

    }


    public ImageView getMedicineImage() {
        return medicineImage;
    }

    public void setMedicineImage(ImageView medicineImage) {
        this.medicineImage = medicineImage;
    }

    public TextView getMedicineTitle() {
        return medicineTitle;
    }

    public void setMedicineTitle(TextView medicineTitle) {
        this.medicineTitle = medicineTitle;
    }

    public TextView getMedicinePrice() {
        return medicinePrice;
    }

    public void setMedicinePrice(TextView medicinePrice) {
        this.medicinePrice = medicinePrice;
    }

    public TextView getMedicineDicount() {
        return medicineDicount;
    }

    public void setMedicineDicount(TextView medicineDicount) {
        this.medicineDicount = medicineDicount;
    }

    public TextView getMedicineOldprice() {
        return medicineOldprice;
    }

    public void setMedicineOldprice(TextView medicineOldprice) {
        this.medicineOldprice = medicineOldprice;
    }


}
