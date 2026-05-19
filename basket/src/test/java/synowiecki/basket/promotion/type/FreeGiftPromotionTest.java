package synowiecki.basket.promotion.type;

import java.util.List;

import org.junit.jupiter.api.Test;

import synowiecki.basket.model.Product;
import synowiecki.basket.promotion.PromotionResult;

import static org.junit.jupiter.api.Assertions.*;

class FreeGiftPromotionTest {

    private final Product gift = new Product("MUG", "Mug", 0);
    private final FreeGiftPromotion promotion = new FreeGiftPromotion(250, gift);

    @Test
    void shouldReturnGiftWhenTotalExceedsThreshold() {
        PromotionResult result = promotion.apply(List.of(
            new Product("P1", "A", 150),
            new Product("P2", "B", 100)
        ), List.of());

        assertEquals(List.of(gift), result.getGifts());
        assertTrue(result.getDiscounts().isEmpty());
    }

    @Test
    void shouldReturnNoGiftWhenTotalDoesNotExceedThreshold() {
        PromotionResult result = promotion.apply(List.of(
            new Product("P1", "A", 100),
            new Product("P2", "B", 100)
        ), List.of());

        assertTrue(result.getGifts().isEmpty());
        assertTrue(result.getDiscounts().isEmpty());
    }
}
