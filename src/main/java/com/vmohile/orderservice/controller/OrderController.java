package com.vmohile.orderservice.controller;

import org.springframework.web.bind.annotation.RestController;
import com.vmohile.orderservice.model.Order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/order")
public class OrderController {

    @PostMapping("/place")
    public ResponseEntity<?> placeOrder(@RequestBody Order order) {
        if (order.getOrderId() == null || order.getOrderId().isEmpty()) {
            return ResponseEntity.badRequest().body("{\"message\": \"Order placement failed. Provide order ID\"}");
        } else if (order.getProduct() == null || order.getProduct().isEmpty()) {
            return ResponseEntity.badRequest().body("{\"message\": \"Order placement failed. Provide Product name\"}");
        } else if (order.getQuantity() == null || order.getQuantity() == 0) {
            return ResponseEntity.badRequest().body("{\"message\": \"Order placement failed. Provide valid quantity\"}");
        } else {
            return ResponseEntity.ok("{\"message\": \"Order placed successfully\"}");
        }
    }
    
}
 