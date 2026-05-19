package synowiecki.basket.promotion.type;

import java.util.ArrayList;
import java.util.List;

import synowiecki.basket.model.Discount;
import synowiecki.basket.model.Product;
import synowiecki.basket.promotion.Promotion;
import synowiecki.basket.promotion.PromotionResult;


public class CouponPromotion implements Promotion {

    private final String code;
    private final double rate;
    private final String coupon;

    public CouponPromotion(String code, double rate, String coupon) {
        this.code = code;
        this.rate = rate;
        this.coupon = coupon;
    }

    @Override
    public PromotionResult apply(List<Product> products, List<Product> used) {

        List<Discount> discounts = new ArrayList<>();
        List<Product> applied = new ArrayList<>();

        for (Product product : products) {

            boolean matchingProduct = code.equals(product.getCode());
            boolean alreadyUsed = used.contains(product);

            if (matchingProduct && !alreadyUsed) {
                discounts.add(new Discount(coupon, product.getPrice() * rate));
                applied.add(product);
                break;
            }
        }

        return new PromotionResult(discounts, List.of(), applied);
    }
}
