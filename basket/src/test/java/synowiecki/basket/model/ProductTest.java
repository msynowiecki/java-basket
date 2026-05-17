package synowiecki.basket.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ProductTest {

    @Test
    void shouldCreateProduct() {
        Product product = new Product("P1", "Laptop", 3000);

        assertEquals("P1", product.getCode());
        assertEquals("Laptop", product.getName());
        assertEquals(3000, product.getPrice());
    }

    @Test
    void shouldRejectNullOrBlankCode() {
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> new Product(null, "Laptop", 3000)),
            () -> assertThrows(IllegalArgumentException.class, () -> new Product("", "Laptop", 3000))
        );
    }

    @Test
    void shouldRejectNullOrBlankName() {
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> new Product("P1", null, 3000)),
            () -> assertThrows(IllegalArgumentException.class, () -> new Product("P1", "", 3000))
        );
    }

    @Test
    void shouldRejectNegativePrice() {
        IllegalArgumentException error = assertThrows(
            IllegalArgumentException.class,
            () -> new Product("P1", "Laptop", -1)
        );

        assertEquals("Product price cannot be negative", error.getMessage());
    }
}
