package com.example.medic.Responses;

import java.util.List;

public class GetOrderByUserResponse {

    private List<OrdersDto> orders;

    private String responseCode;

    private String responseMessage;


    public List<OrdersDto> getOrders() {
        return orders;
    }

    public void setOrders(List<OrdersDto> orders) {
        this.orders = orders;
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
