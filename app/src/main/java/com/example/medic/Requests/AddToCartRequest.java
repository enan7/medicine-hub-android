package com.example.medic.Requests;

public class AddToCartRequest {

    private Long medicineId;
    private String quantity;

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public int getQuantity() {
        return Integer.parseInt(quantity);
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
