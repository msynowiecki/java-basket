package synowiecki.basket.promotion.type;

import java.util.List;
import java.util.Objects;

import synowiecki.basket.model.Product;
import synowiecki.basket.promotion.Promotion;
import synowiecki.basket.promotion.PromotionResult;


public class FreeGiftPromotion implements Promotion {

    private final double threshold;
    private final String gift;

    public FreeGiftPromotion(double threshold, String gift) {
        this.threshold = threshold;
        this.gift = gift;
    }

    @Override
    public PromotionResult apply(List<Product> products) {

        double total = products.stream()
                .filter(Objects::nonNull)
                .mapToDouble(Product::getPrice)
                .sum();

        if (total >= threshold) {
            return new PromotionResult(List.of(), List.of(gift));
        }

        return new PromotionResult(List.of(), List.of());
    }
}
