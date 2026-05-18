package synowiecki.basket.promotion;

import java.util.List;

import synowiecki.basket.model.Discount;


public class PromotionResult {

    private final List<Discount> discounts;
    private final List<String> gifts;

    public PromotionResult(List<Discount> discounts, List<String> gifts) {
        this.discounts = discounts;
        this.gifts = gifts;
    }

    public List<Discount> getDiscounts() { return discounts; }
    public List<String> getGifts() { return gifts; }
}
