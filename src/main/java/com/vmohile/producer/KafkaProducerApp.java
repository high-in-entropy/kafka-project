package com.vmohile.producer;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.KafkaProducer;

import org.apache.kafka.common.serialization.StringSerializer;
import java.util.Properties;

public class KafkaProducerApp {

    public static void main (String[] args) {

        String bootStrapServer = "10.0.0.127:9092";

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServer);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        ProducerRecord<String, String> record = new ProducerRecord<String,String>("first-topic", "This is my test message!!");

        producer.send(record);
        producer.flush();
        producer.close();
    }
}
