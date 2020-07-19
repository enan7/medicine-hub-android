package com.example.medic.Holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.R;

public class MedicineHolder extends RecyclerView.ViewHolder {

    private ImageView medicineImage;
    private TextView medicineTitle, medicinePrice, medicineDicount, medicineOldprice, medicineQuantity, medicineUnit;

    public MedicineHolder(@NonNull View medicineView) {
        super(medicineView);

        this.medicineImage = medicineView.findViewById(R.id.item_image);
        this.medicineTitle = medicineView.findViewById(R.id.item_title);
        this.medicinePrice = medicineView.findViewById(R.id.item_newprice);
        this.medicineDicount = medicineView.findViewById(R.id.item_discount);
        this.medicineOldprice = medicineView.findViewById(R.id.item_oldprice);
        this.medicineQuantity = medicineView.findViewById(R.id.item_qty);
        this.medicineUnit = medicineView.findViewById(R.id.item_unit);

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

    public TextView getMedicineQuantity() {
        return medicineQuantity;
    }

    public void setMedicineQuantity(TextView medicineQuantity) {
        this.medicineQuantity = medicineQuantity;
    }

    public TextView getMedicineUnit() {
        return medicineUnit;
    }

    public void setMedicineUnit(TextView medicineUnit) {
        this.medicineUnit = medicineUnit;
    }
}
