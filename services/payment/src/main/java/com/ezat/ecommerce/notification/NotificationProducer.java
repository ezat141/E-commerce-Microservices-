package com.ezat.ecommerce.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationProducer {
    private final KafkaTemplate<String, PaymentNotificationRequest> kafkaTemplate;

    public void sendNotification(PaymentNotificationRequest notification) {
        log.info("Sending notification with body = < {} >", notification);

        Message<PaymentNotificationRequest> message = MessageBuilder
                .withPayload(notification)
                .setHeader(TOPIC, "payment-topic")
                .build();
        kafkaTemplate.send(message);
    }
}
