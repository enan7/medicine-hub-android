package com.example.medic.Requests;

public class OrderRequest {

    private Long cartId;
    private OrderAdddressDto address;

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public OrderAdddressDto getAddress() {
        return address;
    }

    public void setAddress(OrderAdddressDto address) {
        this.address = address;
    }
}
