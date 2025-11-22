package pl.mg.javatst.pattern.strategy;

import java.math.BigDecimal;
import java.util.function.Predicate;

public interface Discount {

    Predicate<OrderPosition> applicable();

    BigDecimal apply(BigDecimal price);

}
