package com.vmohile.admin;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.yaml.snakeyaml.Yaml;

import java.util.Properties;
import java.util.Collections;
import java.util.concurrent.ExecutionException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;


public class KafkaAdminClient {

    public static final String CONFIG_FILE_PATH = "/home/ubuntu/projects/kafka-project/src/resources/kafka-config.yaml";
    public static void main(String[] args) {
        Map<String, Object> config = loadConfig(CONFIG_FILE_PATH);
        String bootstrapServers = (String) config.get("kafka_bootstrapServers");

        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        try (AdminClient adminClient = AdminClient.create(props)) {

            // Topic Name, num_partitions, replication_factor
            NewTopic topic = new NewTopic("first-topic", 1, (short) 1);
            adminClient.createTopics(Collections.singletonList(topic)).all().get();

            System.out.println("New topic created successfully");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
    // Method to load YAML config file for all kafka extensible properties.
    private static Map<String, Object> loadConfig(String filePath) {

        Yaml yaml = new Yaml();
        try (InputStream inputStream = new FileInputStream(filePath)) {
            return yaml.load(inputStream);
        } catch (IOException e){
            e.printStackTrace();
            return Collections.emptyMap();

        }


    }


}