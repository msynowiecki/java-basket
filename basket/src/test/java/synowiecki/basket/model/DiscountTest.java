package synowiecki.basket.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscountTest {

    @Test
    void shouldCreateDiscount() {
        Discount discount = new Discount("Summer", 150);

        assertEquals("Summer", discount.getName());
        assertEquals(150, discount.getAmount());
    }

    @Test
    void shouldRejectNullOrBlankName() {
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> new Discount(null, 10)),
            () -> assertThrows(IllegalArgumentException.class, () -> new Discount("", 10))
        );
    }

    @Test
    void shouldRejectNegativeAmount() {
        IllegalArgumentException error = assertThrows(
            IllegalArgumentException.class,
            () -> new Discount("Summer", -1)
        );

        assertEquals("Discount amount cannot be negative", error.getMessage());
    }
}
