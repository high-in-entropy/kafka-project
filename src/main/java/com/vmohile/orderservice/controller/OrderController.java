package com.vmohile.orderservice.controller;

import org.springframework.web.bind.annotation.RestController;
import com.vmohile.orderservice.model.Order;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/order")
public class OrderController {

    @PostMapping("/place")
    public String placeOrder(@RequestBody Order order) {

        return "Order placed successfully";
    }
    
}
