package com.example.medic.Responses;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;

public class MedicineResponse implements Serializable {
    private Long medicineId;
    private String medicineName;
    private Double quantity;
    private String unit;
    private Double price;
    private int discount;
    private String description;
    private int qty_Available;
    private String manufacturer;
    private String image;
    private Double calculatedPrice;


    public Double getCalculatedPrice() {
        return calculatedPrice;
    }

    public void setCalculatedPrice(Double calculatedPrice) {
        this.calculatedPrice = calculatedPrice;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQty_Available() {
        return qty_Available;
    }

    public void setQty_Available(int qty_Available) {
        this.qty_Available = qty_Available;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Bitmap getImage() {
        if (image != null) {
            byte[] decodedBytes = Base64.decode(image, Base64.DEFAULT);
            String newDecodedImageUri = new String(decodedBytes, StandardCharsets.UTF_8);
            String base64Image = newDecodedImageUri.split(",")[1];
            byte[] decodedImageUri = Base64.decode(base64Image, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(decodedImageUri, 0, decodedImageUri.length);
            return bitmap;
        }
        return null;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
