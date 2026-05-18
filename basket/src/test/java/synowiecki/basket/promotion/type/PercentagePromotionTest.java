package synowiecki.basket.promotion.type;

import java.util.List;

import org.junit.jupiter.api.Test;

import synowiecki.basket.model.Product;
import synowiecki.basket.promotion.PromotionResult;

import static org.junit.jupiter.api.Assertions.*;

class PercentagePromotionTest {

    private final PercentagePromotion promotion = new PercentagePromotion(300, 0.05);

    @Test
    void shouldReturnNoDiscountWhenTotalIsNotAboveThreshold() {
        PromotionResult result = promotion.apply(List.of(
            new Product("P1", "A", 100),
            new Product("P2", "B", 200)
        ));

        assertTrue(result.getDiscounts().isEmpty());
        assertTrue(result.getGifts().isEmpty());
    }

    @Test
    void shouldApplyFivePercentDiscountWhenTotalAboveThreshold() {
        PromotionResult result = promotion.apply(List.of(
            new Product("P1", "A", 150),
            new Product("P2", "B", 200)
        ));

        assertEquals(1, result.getDiscounts().size());
        assertEquals(17.5, result.getDiscounts().get(0).getAmount(), 1e-9);
    }
}
