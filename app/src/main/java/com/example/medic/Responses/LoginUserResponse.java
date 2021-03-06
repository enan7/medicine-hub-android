package com.example.medic.Responses;

public class LoginUserResponse {

    private String responseCode;
    private String responseMessage;
    private String jwtToken;
    private int cartItems;



    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
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

    public int getCartItems() {
        return cartItems;
    }

    public void setCartItems(int cartItems) {
        this.cartItems = cartItems;
    }
}
