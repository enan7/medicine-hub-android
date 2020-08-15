package com.example.medic.Responses;

import java.util.List;

public class CartDetailResponse {


    private String responseCode;
    private String responseMessage;
    private Long cartId;
    private Double totalPrice;
    private List<CartDetailDTO> cartItems;


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

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<CartDetailDTO> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartDetailDTO> cartItems) {
        this.cartItems = cartItems;
    }
}
