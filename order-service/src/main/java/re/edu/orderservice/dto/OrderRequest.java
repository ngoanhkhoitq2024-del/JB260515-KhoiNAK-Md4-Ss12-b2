package re.edu.orderservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderRequest {
    private String customerName;

    private String customerEmail;

    private Long productId;

    private Integer quantity;

    private BigDecimal totalAmount;
}