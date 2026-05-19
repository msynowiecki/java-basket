package synowiecki.basket.promotion;

import java.util.List;

import org.junit.jupiter.api.Test;

import synowiecki.basket.model.Discount;
import synowiecki.basket.model.Product;

import static org.junit.jupiter.api.Assertions.*;

class PromotionResultTest {

    @Test
    void shouldHoldDiscountsAndGifts() {
        Discount discount = new Discount("5%", 50);
        Product gift = new Product("MUG", "Mug", 0);
        PromotionResult result = new PromotionResult(List.of(discount), List.of(gift), List.of());

        assertEquals(List.of(discount), result.getDiscounts());
        assertEquals(List.of(gift), result.getGifts());
        assertTrue(result.getUsed().isEmpty());
    }
}
