package synowiecki.basket.promotion.type;

import java.util.List;

import org.junit.jupiter.api.Test;

import synowiecki.basket.model.Product;
import synowiecki.basket.promotion.PromotionResult;

import static org.junit.jupiter.api.Assertions.*;

class GetMorePromotionTest {

    private final GetMorePromotion promotion = new GetMorePromotion(2, 1);

    @Test
    void shouldReturnNoDiscountForLessThanThreeProducts() {
        PromotionResult result = promotion.apply(List.of(
            new Product("P1", "A", 100),
            new Product("P2", "B", 200)
        ));

        assertTrue(result.getDiscounts().isEmpty());
        assertTrue(result.getGifts().isEmpty());
    }

    @Test
    void shouldReturnCheapestAsDiscountForThreeOrMoreProducts() {
        PromotionResult result = promotion.apply(List.of(
            new Product("P1", "A", 300),
            new Product("P2", "B", 100),
            new Product("P3", "C", 200)
        ));

        assertEquals(1, result.getDiscounts().size());
        assertEquals(100, result.getDiscounts().get(0).getAmount());
    }
}
