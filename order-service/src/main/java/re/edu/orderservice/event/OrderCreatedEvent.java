package re.edu.orderservice.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedEvent {
    private Long orderId;

    private String customerName;

    private String customerEmail;

    private Long productId;

    private Integer quantity;

    private BigDecimal totalAmount;

    private LocalDateTime createdAt;
}