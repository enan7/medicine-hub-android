package com.example.medic.Responses;

import java.util.List;

public class OrderDetailsResponse {

   private String responseCode;
   private String responseMessage;
    private OrdersDto order;

    private List<OrderedItemsDto> orderedItems;
    private OrderAddressDto orderAddress;

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

    public OrdersDto getOrder() {
        return order;
    }
    public void setOrder(OrdersDto order) {
        this.order = order;
    }

    public List<OrderedItemsDto> getOrderedItems() {
        return orderedItems;
    }
    public void setOrderedItems(List<OrderedItemsDto> orderedItems) {
        this.orderedItems = orderedItems;
    }
    public OrderAddressDto getOrderAddress() {
        return orderAddress;
    }
    public void setOrderAddress(OrderAddressDto orderAddress) {
        this.orderAddress = orderAddress;
    }





}