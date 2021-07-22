package pl.mg.javatst.pattern.strategy;

import java.math.BigDecimal;
import java.time.*;
import java.util.function.Predicate;

import static java.time.Month.JULY;

public class CyberMondayDiscount implements Discount {

    @Override
    public Predicate<OrderPosition> applicable() {
        return position -> {
            final var now = LocalDateTime.now(Clock.systemUTC());
            return now.toLocalDate().equals(getCyberMondayFor(now.getYear()));
        };
    }

    @Override
    public BigDecimal apply(BigDecimal price) {
        return price.multiply(BigDecimal.valueOf(0.65));
    }

    private LocalDate getCyberMondayFor(Integer year) {
        return LocalDate.of(year, JULY, 21);
    }
}
