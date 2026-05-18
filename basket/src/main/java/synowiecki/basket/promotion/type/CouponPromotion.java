package synowiecki.basket.promotion.type;

import java.util.ArrayList;
import java.util.List;

import synowiecki.basket.model.Discount;
import synowiecki.basket.model.Product;
import synowiecki.basket.promotion.Promotion;
import synowiecki.basket.promotion.PromotionResult;


public class CouponPromotion implements Promotion {

    private final String productCode;
    private final double rate;

    public CouponPromotion(String productCode, double rate) {
        this.productCode = productCode;
        this.rate = rate;
    }

    @Override
    public PromotionResult apply(List<Product> products) {

        List<Discount> discounts = new ArrayList<>();

        for (Product product : products) {
            if (productCode.equals(product.getCode())) {
                discounts.add(new Discount(rate * 100 + "%", product.getPrice() * rate));
            }
        }

        return new PromotionResult(discounts, List.of());
    }
}
