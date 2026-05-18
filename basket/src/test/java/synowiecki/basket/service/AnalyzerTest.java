package synowiecki.basket.service;

import java.util.List;

import org.junit.jupiter.api.Test;

import synowiecki.basket.model.Product;

import static org.junit.jupiter.api.Assertions.*;

class AnalyzerTest {

    private final Analyzer analyzer = new Analyzer();

    @Test
    void shouldFindCheapestProducts() {
        List<Product> products = List.of(
            new Product("P1", "A", 300),
            new Product("P2", "B", 100),
            new Product("P3", "C", 200)
        );

        assertEquals(List.of("P2", "P3"),
            analyzer.findCheapest(products, 2).stream().map(Product::getCode).toList());
    }

    @Test
    void shouldFindMostExpensiveProducts() {
        List<Product> products = List.of(
            new Product("P1", "A", 300),
            new Product("P2", "B", 100),
            new Product("P3", "C", 200)
        );

        assertEquals(List.of("P1", "P3"),
            analyzer.findMostExpensive(products, 2).stream().map(Product::getCode).toList());
    }

    @Test
    void shouldSortWithDefaultComparatorIfNull() {
        List<Product> products = List.of(
            new Product("P1", "Z", 100),
            new Product("P2", "A", 50)
        );

        assertEquals(List.of("P2", "P1"),
            analyzer.sort(products, null).stream().map(Product::getCode).toList());
    }

    @Test
    void shouldRejectInvalidNumberAndEmptyCollections() {
        List<Product> products = List.of(new Product("P1", "A", 100));

        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> analyzer.findCheapest(products, 0)),
            () -> assertThrows(IllegalArgumentException.class, () -> analyzer.findMostExpensive(products, -1)),
            () -> assertThrows(IllegalArgumentException.class, () -> analyzer.sort(List.of(), null)),
            () -> assertThrows(IllegalArgumentException.class, () -> analyzer.findCheapest(null, 1))
        );
    }
}
