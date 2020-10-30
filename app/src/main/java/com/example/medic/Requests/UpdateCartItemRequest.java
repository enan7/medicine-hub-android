package com.example.medic.Requests;

public class UpdateCartItemRequest {
    private long itemId;
    private int quantity;
    public long getItemId() {
        return itemId;
    }
    public void setItemId(long itemId) {
        this.itemId = itemId;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
