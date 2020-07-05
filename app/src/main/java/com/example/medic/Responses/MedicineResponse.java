package com.example.medic.Responses;

import android.util.Base64;

import java.nio.charset.StandardCharsets;

public class MedicineResponse {
    private Long medicineId;
    private String medicineName;
    private Double quantity;
    private String unit;
    private Double price;
    private int discount;
    private String description;
    private int qty_Available;
    private String manufacturer;
    private String  image;

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

    public String getImage() {
        if (image != null) {
            byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
            String newDecodedImageUri = new String(decodedString, StandardCharsets.UTF_8);
            String base64Image = newDecodedImageUri.split(",")[1];
            return base64Image;
        }
        return null;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
