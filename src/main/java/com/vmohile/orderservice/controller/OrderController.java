package com.vmohile.orderservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vmohile.orderservice.model.Order;
import com.vmohile.orderservice.service.KafkaProducerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @PostMapping("/place")
    public ResponseEntity<String> placeOrder(@RequestBody Order order) {
        if (order.getOrderId() == null || order.getOrderId().isEmpty()) {
            return new ResponseEntity<>("Order placement failed. Provide Order ID.", HttpStatus.BAD_REQUEST);
        } else if (order.getProduct() == null || order.getProduct().isEmpty()) {
            return new ResponseEntity<>("Order placement failed. Provide Product name.", HttpStatus.BAD_REQUEST);
        } else if (order.getQuantity() == null || order.getQuantity() == 0) {
            return new ResponseEntity<>("Order placement failed. Provide quantity.", HttpStatus.BAD_REQUEST);
        } else {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                String orderJSON = objectMapper.writeValueAsString(order);
                kafkaProducerService.sendOrderMessage(orderJSON);
                return new ResponseEntity<>("Order successfully placed!", HttpStatus.ACCEPTED);
            } catch (Exception e) {
                return new ResponseEntity<>("Failure while sending orders to Kafka topic", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
