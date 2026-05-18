package synowiecki.basket.promotion.type;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import synowiecki.basket.model.Discount;
import synowiecki.basket.model.Product;
import synowiecki.basket.promotion.Promotion;
import synowiecki.basket.promotion.PromotionResult;


public class GetMorePromotion implements Promotion {

    @Override
    public PromotionResult apply(List<Product> products) {

        if (products.size() < 3) {
            return new PromotionResult(List.of(), List.of());
        }

        List<Product> sorted = new ArrayList<>(products);
        sorted.sort(Comparator.comparingDouble(Product::getPrice));

        Product free = sorted.get(0);

        return new PromotionResult(
            List.of(new Discount("2+1", free.getPrice())),
            List.of()
        );
    }
}
