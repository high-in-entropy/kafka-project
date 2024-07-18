package com.vmohile.orderservice.service;

import org.apache.kafka.clients.producer.KafkaProducer;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class KafkaProducerService {

    public static final String TOPIC = "orders";

    @Autowired
    private KafkaProducer<String, String> kafkaProducer;
 

    public void sendOrderMessage(String message) {
        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, message);
        kafkaProducer.send(record);
    }
}
