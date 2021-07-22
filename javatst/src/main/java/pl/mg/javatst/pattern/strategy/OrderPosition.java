package pl.mg.javatst.pattern.strategy;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
public class OrderPosition {

    private long quantity;
    private BigDecimal price;
}
