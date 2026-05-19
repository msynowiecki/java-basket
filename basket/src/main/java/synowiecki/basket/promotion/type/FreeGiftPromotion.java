package synowiecki.basket.promotion.type;

import java.util.List;
import java.util.Objects;

import synowiecki.basket.model.Product;
import synowiecki.basket.promotion.Promotion;
import synowiecki.basket.promotion.PromotionResult;


public class FreeGiftPromotion implements Promotion {

    private final double threshold;
    private final Product gift;

    public FreeGiftPromotion(double threshold, Product gift) {
        this.threshold = threshold;
        this.gift = Objects.requireNonNull(gift, "Gift product cannot be null");
    }

    @Override
    public PromotionResult apply(List<Product> products, List<Product> used) {

        double total = products.stream()
            .filter(Objects::nonNull)
            .mapToDouble(Product::getPrice)
            .sum();

        if (total >= threshold) {
            return new PromotionResult(List.of(), List.of(gift), List.of());
        }

        return new PromotionResult(List.of(), List.of(), List.of());
    }
}
