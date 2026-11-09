package re.edu.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class OrderResponse {
    private Long id;

    private String customerName;

    private String customerEmail;

    private Long productId;

    private Integer quantity;

    private BigDecimal totalAmount;

    private String status;

    private LocalDateTime createdAt;
}