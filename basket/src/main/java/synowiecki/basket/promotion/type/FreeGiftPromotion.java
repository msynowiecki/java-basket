package synowiecki.basket.promotion.type;

import java.util.List;
import java.util.Objects;

import synowiecki.basket.model.Product;
import synowiecki.basket.promotion.Promotion;
import synowiecki.basket.promotion.PromotionResult;


public class FreeGiftPromotion implements Promotion{

    public PromotionResult apply(List<Product> products) {

        double total = products.stream()
            .filter(Objects::nonNull)
            .mapToDouble(Product::getPrice)
            .sum();

        if (total > 200) {
            return new PromotionResult(
                List.of(),
                List.of("Mug")
            );
        }

        return new PromotionResult(List.of(), List.of());
    }
}
