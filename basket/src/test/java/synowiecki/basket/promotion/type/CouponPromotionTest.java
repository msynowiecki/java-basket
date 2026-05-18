package synowiecki.basket.promotion.type;

import java.util.List;

import org.junit.jupiter.api.Test;

import synowiecki.basket.model.Product;
import synowiecki.basket.promotion.PromotionResult;

import static org.junit.jupiter.api.Assertions.*;

class CouponPromotionTest {

    private final CouponPromotion promotion = new CouponPromotion("ABC", 0.3);

    @Test
    void shouldApplyCouponToMatchingProductCode() {
        PromotionResult result = promotion.apply(List.of(
            new Product("ABC", "Couponed", 200),
            new Product("P2", "Regular", 100)
        ));

        assertEquals(1, result.getDiscounts().size());
        assertEquals(60, result.getDiscounts().get(0).getAmount(), 1e-9);
    }

    @Test
    void shouldNotDiscountNonMatchingProducts() {
        PromotionResult result = promotion.apply(List.of(
            new Product("P1", "A", 200),
            new Product("P2", "B", 100)
        ));

        assertTrue(result.getDiscounts().isEmpty());
        assertTrue(result.getGifts().isEmpty());
    }
}
