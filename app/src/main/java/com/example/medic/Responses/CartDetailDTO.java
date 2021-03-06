package com.example.medic.Responses;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.nio.charset.StandardCharsets;

public class CartDetailDTO {

    private Long itemId;
    private Double itemPriceWithoutQuantityAndDiscount;
    private Double itemPriceWithSingleQuantityAndDiscount;
    private Double itemPriceWithQuantityAndDiscount;
    private int itemQuantity;
    private int medicineId;
    private String medicineName;
    private String medicineIcon;
    private String description;
    private String manufacturer;
    private int medicineQuantity;
    private String medicineUnit;
    private int discount;

    public Double getItemPriceWithoutQuantityAndDiscount() {
        return itemPriceWithoutQuantityAndDiscount;
    }

    public void setItemPriceWithoutQuantityAndDiscount(Double itemPriceWithoutQuantityAndDiscount) {
        this.itemPriceWithoutQuantityAndDiscount = itemPriceWithoutQuantityAndDiscount;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Double getItemPriceWithSingleQuantityAndDiscount() {
        return itemPriceWithSingleQuantityAndDiscount;
    }

    public void setItemPriceWithSingleQuantityAndDiscount(Double itemPriceWithSingleQuantityAndDiscount) {
        this.itemPriceWithSingleQuantityAndDiscount = itemPriceWithSingleQuantityAndDiscount;
    }

    public Double getItemPriceWithQuantityAndDiscount() {
        return itemPriceWithQuantityAndDiscount;
    }

    public void setItemPriceWithQuantityAndDiscount(Double itemPriceWithQuantityAndDiscount) {
        this.itemPriceWithQuantityAndDiscount = itemPriceWithQuantityAndDiscount;
    }


    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }



    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }


    public Bitmap getMedicineIcon() {
        if (medicineIcon != null) {
            byte[] decodedBytes = Base64.decode(medicineIcon, Base64.DEFAULT);
            String newDecodedImageUri = new String(decodedBytes, StandardCharsets.UTF_8);
            String base64Image = newDecodedImageUri.split(",")[1];
            byte[] decodedImageUri = Base64.decode(base64Image, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(decodedImageUri, 0, decodedImageUri.length);
            return bitmap;
        }
        return null;
    }

    public void getMedicineIcon(String image) {
        this.medicineIcon = image;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getMedicineQuantity() {
        return medicineQuantity;
    }

    public void setMedicineQuantity(int medicineQuantity) {
        this.medicineQuantity = medicineQuantity;
    }

    public String getMedicineUnit() {
        return medicineUnit;
    }

    public void setMedicineUnit(String medicineUnit) {
        this.medicineUnit = medicineUnit;
    }


}
