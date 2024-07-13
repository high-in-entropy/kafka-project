package com.vmohile.orderservice.model;

public class Order {

    private String orderId;
    private String product;
    private int quantity;

    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getProduct() {
        return product;
    }
    public void setProduct(String product) {
        this.product = product;
    }
    public int getQuamtity() {
        return quantity;
    }
    public void setQuamtity(int quantity) {
        this.quantity = quantity;
    }  
}
