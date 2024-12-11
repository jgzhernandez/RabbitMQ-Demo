package com.example.RabbitMQ_Demo.controller;

import com.example.RabbitMQ_Demo.QueueSender;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testq")
public class QueueController {
    public QueueController(RabbitTemplate queueSender) {
        this.queueSender = queueSender;
    }

    private final RabbitTemplate queueSender;

    @GetMapping
    public String send(){
//        queueSender.send("test message");
//        return "ok. done";
        String msg = "test message";

        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("kyle", "megino");
        Message message = new Message(msg.getBytes(), messageProperties);

        queueSender.convertAndSend("test-exchange", "routing-key-testq", message);
        return "ok. done";
    }
}
