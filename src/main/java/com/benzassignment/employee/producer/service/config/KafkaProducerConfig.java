package com.benzassignment.employee.producer.service.config;

import com.benzassignment.employee.producer.service.model.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Properties;

@Configuration
public class KafkaProducerConfig {

    @Value("${kafka.producer.bootstrap-servers}")
    public String bootstrapServers = "";
    @Value("${kafka.producer.keySerializer}")
    public String keySerializer = "";

    @Bean
    public ProducerFactory<String, Employee> producerFactory(){
        Properties producerConfig = new Properties();

        producerConfig.put("bootstrap.servers",bootstrapServers);
        producerConfig.put("key.serializer",keySerializer);
        producerConfig.put("value.serializer",JsonSerializer.class);

        return new DefaultKafkaProducerFactory(producerConfig);
    }

    @Bean
    public KafkaTemplate<String, Employee> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }
}
