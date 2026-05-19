package synowiecki.basket.promotion.type;

import java.util.List;

import org.junit.jupiter.api.Test;

import synowiecki.basket.model.Product;
import synowiecki.basket.promotion.PromotionResult;

import static org.junit.jupiter.api.Assertions.*;

class GetMorePromotionTest {

    private final GetMorePromotion promotion = new GetMorePromotion("A", 2, 1);

    @Test
    void shouldReturnNoDiscountForLessThanThreeMatchingProducts() {
        PromotionResult result = promotion.apply(List.of(
            new Product("A", "Product A1", 200),
            new Product("B", "Product B1", 200)
        ), List.of());

        assertTrue(result.getDiscounts().isEmpty());
        assertTrue(result.getGifts().isEmpty());
    }

    @Test
    void shouldDiscountForThreeOrMoreMatchingProducts() {
        PromotionResult result = promotion.apply(List.of(
            new Product("A", "Product A1", 200),
            new Product("A", "Product A2", 200),
            new Product("A", "Product A3", 200),
            new Product("B", "Product B1", 200)
        ), List.of());

        assertEquals(1, result.getDiscounts().size());
        assertEquals(200, result.getDiscounts().get(0).getAmount());
        assertTrue(result.getGifts().isEmpty());
    }
}
