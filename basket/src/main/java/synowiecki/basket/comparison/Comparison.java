package synowiecki.basket.comparison;

import java.util.Comparator;

import synowiecki.basket.model.Product;

public class Comparison {
    
    private Comparison() {}

    public static final Comparator<Product> BY_PRICE_DESC_NAME_ASC =
        Comparator.comparingDouble(Product::getPrice)
            .reversed()
            .thenComparing(Product::getName);

    public static final Comparator<Product> BY_PRICE_ASC_NAME_ASC =
        Comparator.comparingDouble(Product::getPrice)
            .thenComparing(Product::getName);

    public static final Comparator<Product> BY_NAME_ASC_PRICE_DESC =
        Comparator.comparing(Product::getName)
            .thenComparing(Comparator.comparingDouble(Product::getPrice).reversed());
}
