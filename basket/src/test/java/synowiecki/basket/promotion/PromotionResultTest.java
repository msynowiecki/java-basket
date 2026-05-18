package synowiecki.basket.promotion;

import java.util.List;

import org.junit.jupiter.api.Test;

import synowiecki.basket.model.Discount;

import static org.junit.jupiter.api.Assertions.*;

class PromotionResultTest {

    @Test
    void shouldHoldDiscountsAndGifts() {
        Discount discount = new Discount("5%", 50);
        PromotionResult result = new PromotionResult(List.of(discount), List.of("Mug"));

        assertEquals(List.of(discount), result.getDiscounts());
        assertEquals(List.of("Mug"), result.getGifts());
    }
}
