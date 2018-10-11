package com.example.demoasdasd;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.header.Headers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.LoggingProducerListener;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Arrays;

@SpringBootApplication
public class DemoasdasdApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoasdasdApplication.class, args);
    }


    @Service
    public static class lala implements CommandLineRunner {

        @Autowired
        KafkaTemplate<String, String> kafkaTemplate;

        @Override
        public void run(String... args) throws Exception {
            while (true) {
                ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send("hello", "hello");
                RecordMetadata recordMetadata = send.get().getRecordMetadata();
                Thread.sleep(1000);
            }
        }
    }
    @Service
    public static class consumer{

        Log log = LogFactory.getLog(consumer.class);

        @KafkaListener(topics = "hello")
        public void handle(ConsumerRecord<String,String> ss){
            log.info(ss.value());

        }

    }




}
