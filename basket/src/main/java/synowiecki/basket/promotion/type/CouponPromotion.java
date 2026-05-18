package synowiecki.basket.promotion.type;

import java.util.ArrayList;
import java.util.List;

import synowiecki.basket.model.Discount;
import synowiecki.basket.model.Product;
import synowiecki.basket.promotion.Promotion;
import synowiecki.basket.promotion.PromotionResult;


public class CouponPromotion implements Promotion {

    public PromotionResult apply(List<Product> products) {

        List<Discount> discounts = new ArrayList<>();

        for (Product product : products) {
            if (product.getCode().equals("ABC")) {
                discounts.add(new Discount("30%", product.getPrice() * 0.3));
            }
        }

        return new PromotionResult(discounts, List.of());
    }
}
