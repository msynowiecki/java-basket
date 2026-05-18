package synowiecki.basket.service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import synowiecki.basket.model.Product;
import synowiecki.basket.comparison.Comparison;


public class Analyzer {

    public List<Product> findCheapest(List<Product> products, int number) {

        validate(products);

        if (number <= 0) {
            throw new IllegalArgumentException("Number must be positive");
        }

        return products.stream()
            .filter(Objects::nonNull)
            .sorted(Comparison.BY_PRICE_ASC_NAME_ASC)
            .limit(number)
            .toList();
    }

    public List<Product> findMostExpensive(List<Product> products, int number) {

        validate(products);

        if (number <= 0) {
            throw new IllegalArgumentException("Number must be positive");
        }

        return products.stream()
            .filter(Objects::nonNull)
            .sorted(Comparison.BY_PRICE_DESC_NAME_ASC)
            .limit(number)
            .toList();
    }

    public List<Product> sort(List<Product> products, Comparator<Product> comparator) {

        validate(products);

        Comparator<Product> effective =
            comparator != null
                ? comparator
                : Comparison.BY_PRICE_ASC_NAME_ASC;

        return products.stream()
            .filter(Objects::nonNull)
            .sorted(effective)
            .toList();
    }

    private void validate(List<Product> products) {
        if (products == null || products.isEmpty()) {
            throw new IllegalArgumentException("Products cannot be null or empty");
        }
    }
}
