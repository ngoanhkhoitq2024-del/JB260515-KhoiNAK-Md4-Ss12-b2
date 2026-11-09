package re.edu.orderservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import re.edu.orderservice.dto.OrderRequest;
import re.edu.orderservice.dto.OrderResponse;
import re.edu.orderservice.entity.Order;
import re.edu.orderservice.event.OrderCreatedEvent;
import re.edu.orderservice.exception.OrderNotFoundException;
import re.edu.orderservice.kafka.OrderEventProducer;
import re.edu.orderservice.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderEventProducer orderEventProducer;

    // Health check
    public String healthCheck() {
        return "Order Service is Up";
    }

    // Get order by ID
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(
                        "Không tìm thấy đơn hàng có id: " + id
                ));
    }

    // Get all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Create order and publish event
    @Transactional
    public OrderResponse createOrder(OrderRequest request) {

        // 1. Chuyển Request thành Entity
        Order order = new Order();

        order.setCustomerName(request.getCustomerName());
        order.setCustomerEmail(request.getCustomerEmail());
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());
        order.setTotalAmount(request.getTotalAmount());
        order.setStatus("CREATED");
        order.setCreatedAt(LocalDateTime.now());

        // 2. Lưu PostgreSQL
        Order savedOrder = orderRepository.save(order);

        // 3. Tạo Event
        OrderCreatedEvent event = new OrderCreatedEvent(
                savedOrder.getId(),
                savedOrder.getCustomerName(),
                savedOrder.getCustomerEmail(),
                savedOrder.getProductId(),
                savedOrder.getQuantity(),
                savedOrder.getTotalAmount(),
                savedOrder.getCreatedAt()
        );

        // 4. Gửi Event lên Kafka
        orderEventProducer.publishOrderCreatedEvent(event);

        // 5. Trả response
        return new OrderResponse(
                savedOrder.getId(),
                savedOrder.getCustomerName(),
                savedOrder.getCustomerEmail(),
                savedOrder.getProductId(),
                savedOrder.getQuantity(),
                savedOrder.getTotalAmount(),
                savedOrder.getStatus(),
                savedOrder.getCreatedAt()
        );
    }
}
