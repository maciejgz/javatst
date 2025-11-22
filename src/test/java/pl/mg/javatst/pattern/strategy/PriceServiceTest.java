package pl.mg.javatst.pattern.strategy;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class PriceServiceTest {

    @InjectMocks
    PriceService priceService;

    @Spy
    CyberMondayDiscount cyberMondayDiscount;

    List<Discount> discounts;

    @Before
    public void before() {
        discounts = Collections.singletonList(cyberMondayDiscount);
        priceService.setDiscounts(discounts);
    }

    @Test
    public void incorrectCyberMondayDate() {
        OrderPosition orderPosition = new OrderPosition(3, BigDecimal.valueOf(10.5));
        assertEquals(0, priceService.calculatePrice(orderPosition)
                .compareTo(BigDecimal.valueOf(31.5)));
    }

}