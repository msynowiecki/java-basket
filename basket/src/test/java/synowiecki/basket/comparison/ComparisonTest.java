package synowiecki.basket.comparison;

import java.util.List;

import org.junit.jupiter.api.Test;

import synowiecki.basket.model.Product;

import static org.junit.jupiter.api.Assertions.*;


class ComparisonTest {

    @Test
    void shouldSortByPriceDescThenNameAsc() {
        Product low = new Product("P1", "Apple", 100);
        Product high = new Product("P2", "Banana", 200);
        Product samePrice = new Product("P3", "Avocado", 200);

        List<Product> sorted = List.of(low, high, samePrice).stream()
            .sorted(Comparison.BY_PRICE_DESC_NAME_ASC)
            .toList();

        assertEquals(List.of(samePrice, high, low), sorted);
    }

    @Test
    void shouldSortByPriceAscThenNameAsc() {
        Product low = new Product("P1", "Banana", 100);
        Product high = new Product("P2", "Apple", 200);
        Product samePrice = new Product("P3", "Cherry", 200);

        List<Product> sorted = List.of(high, samePrice, low).stream()
            .sorted(Comparison.BY_PRICE_ASC_NAME_ASC)
            .toList();

        assertEquals(List.of(low, high, samePrice), sorted);
    }

    @Test
    void shouldSortByNameAscThenPriceDesc() {
        Product aLow = new Product("P1", "Apple", 100);
        Product aHigh = new Product("P2", "Apple", 200);
        Product b = new Product("P3", "Banana", 150);

        List<Product> sorted = List.of(b, aLow, aHigh).stream()
            .sorted(Comparison.BY_NAME_ASC_PRICE_DESC)
            .toList();

        assertEquals(List.of(aHigh, aLow, b), sorted);
    }
}
