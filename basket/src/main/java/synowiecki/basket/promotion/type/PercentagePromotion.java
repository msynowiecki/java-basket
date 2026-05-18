package synowiecki.basket.promotion.type;

import java.util.List;
import java.util.Objects;

import synowiecki.basket.model.Discount;
import synowiecki.basket.model.Product;
import synowiecki.basket.promotion.Promotion;
import synowiecki.basket.promotion.PromotionResult;


public class PercentagePromotion implements Promotion {

    private final double threshold;
    private final double rate;

    public PercentagePromotion(double threshold, double rate) {
        this.threshold = threshold;
        this.rate = rate;
    }

    @Override
    public PromotionResult apply(List<Product> products) {

        double total = products.stream()
                .filter(Objects::nonNull)
                .mapToDouble(Product::getPrice)
                .sum();

        if (total <= threshold) {
            return new PromotionResult(List.of(), List.of());
        }

        double discount = total * rate;

        return new PromotionResult(
                List.of(new Discount(rate * 100 + "%", discount)),
                List.of()
        );
    }
}
