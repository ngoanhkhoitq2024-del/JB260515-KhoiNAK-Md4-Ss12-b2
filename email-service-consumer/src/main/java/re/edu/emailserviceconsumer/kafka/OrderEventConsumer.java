package re.edu.emailserviceconsumer.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import re.edu.emailserviceconsumer.service.EmailService;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderEventConsumer {
    private final EmailService emailService;

    @KafkaListener(
            topics = "order-events",
            groupId = "email-group"
    )
    public void consume(OrderCreatedEvent event) {

        log.info("Received OrderCreatedEvent: {}", event.getOrderId());

        emailService.sendOrderConfirmation(event);
    }
}