package pl.mg.javatst.pattern.strategy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
public class PriceService {

    @Setter
    @Getter
    private List<Discount> discounts;

    public BigDecimal calculatePrice(OrderPosition position) {
        BigDecimal price = position.getPrice().multiply(BigDecimal.valueOf(position.getQuantity()));

        for (Discount discount : discounts) {
            if (discount.applicable().test(position)) {
                price = discount.apply(price);
            }
        }
        return price;
    }
}
