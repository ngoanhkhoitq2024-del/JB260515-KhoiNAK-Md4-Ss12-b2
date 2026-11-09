package re.edu.emailserviceconsumer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import re.edu.emailserviceconsumer.kafka.OrderCreatedEvent;

@Service
@Slf4j
public class EmailService {
    public void sendOrderConfirmation(OrderCreatedEvent event) {
        log.info("========== EMAIL SERVICE ==========");
        log.info("Sending email...");
        log.info("To: {}", event.getCustomerEmail());
        log.info("Customer: {}", event.getCustomerName());
        log.info("Order ID: {}", event.getOrderId());
        log.info("Product ID: {}", event.getProductId());
        log.info("Quantity: {}", event.getQuantity());
        log.info("Total amount: {}", event.getTotalAmount());
        log.info("Email sent successfully!");
    }
}