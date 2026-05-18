package synowiecki.basket.promotion.type;

import java.util.List;
import java.util.Objects;

import synowiecki.basket.model.Discount;
import synowiecki.basket.model.Product;
import synowiecki.basket.promotion.Promotion;
import synowiecki.basket.promotion.PromotionResult;


public class PercentagePromotion implements Promotion {

    public PromotionResult apply(List<Product> products) {

        double total = products.stream()
            .filter(Objects::nonNull)
            .mapToDouble(Product::getPrice)
            .sum();

        if (total <= 300) {
            return new PromotionResult(List.of(), List.of());
        }

        double discount = total * 0.05;

        return new PromotionResult(
            List.of(new Discount("5%", discount)),
            List.of()
        );
    }
}
