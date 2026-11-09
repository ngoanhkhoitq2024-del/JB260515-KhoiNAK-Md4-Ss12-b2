package re.edu.inventoryservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final JdbcTemplate jdbcTemplate;

    public boolean deductStock(Long productId, Integer quantity) {

        String sql = """
                UPDATE products
                SET stock = stock - ?
                WHERE id = ? AND stock >= ?
                """;

        int updated = jdbcTemplate.update(
                sql,
                quantity,
                productId,
                quantity
        );

        return updated > 0;
    }
}