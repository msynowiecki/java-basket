package synowiecki.basket.promotion;

import java.util.List;

import synowiecki.basket.model.Discount;
import synowiecki.basket.model.Product;


public class PromotionResult {

    private final List<Discount> discounts;
    private final List<Product> gifts;
    private final List<Product> used;

    public PromotionResult(List<Discount> discounts, List<Product> gifts, List<Product> used) {
        this.discounts = discounts;
        this.gifts = gifts;
        this.used = used;
    }

    public List<Discount> getDiscounts() { return discounts; }
    public List<Product> getGifts() { return gifts; }
    public List<Product> getUsed() { return used; }
}
