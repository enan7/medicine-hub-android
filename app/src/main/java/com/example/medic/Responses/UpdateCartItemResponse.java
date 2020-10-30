package com.example.medic.Responses;

public class UpdateCartItemResponse {
    private Double cartTotalPrice;
    private Double itemPrice;
    private String responseCode;
    private String responseMessage;

    public Double getCartTotalPrice() {
        return cartTotalPrice;
    }

    public void setCartTotalPrice(Double cartTotalPrice) {
        this.cartTotalPrice = cartTotalPrice;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
