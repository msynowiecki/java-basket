package synowiecki.basket.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import synowiecki.basket.promotion.Promotion;
import synowiecki.basket.promotion.PromotionResult;


public class Cart {

    private final List<Product> products = new ArrayList<>();
    private final List<Promotion> promotions = new ArrayList<>();


    public void addProduct(Product product) {
        Objects.requireNonNull(product);
        products.add(product);
    }

    public void removeProduct(Product product) {
        Objects.requireNonNull(product);
        products.remove(product);
    }

    public void addPromotion(Promotion promotion) {
        Objects.requireNonNull(promotion);
        promotions.add(promotion);
    }

    public void removePromotion(Promotion promotion) {
        Objects.requireNonNull(promotion);
        promotions.remove(promotion);
    }

    public List<Product> getProducts() { return Collections.unmodifiableList(products); }
    public List<Promotion> getPromotions() { return Collections.unmodifiableList(promotions); }

    public PromotionResult applyPromotions() {

        List<Discount> discounts = new ArrayList<>();
        List<String> gifts = new ArrayList<>();

        for (Promotion promotion : promotions) {
            PromotionResult result = promotion.apply(products);

            discounts.addAll(result.getDiscounts());
            gifts.addAll(result.getGifts());
        }

        return new PromotionResult(discounts, gifts);
    }

    public double calculateTotal() {

        double total = products.stream()
            .filter(Objects::nonNull)
            .mapToDouble(Product::getPrice)
            .sum();

        double discounted = 0;
        List<String> gifts = new ArrayList<>();

        for (Promotion promotion : promotions) {
            PromotionResult result = promotion.apply(products);

            discounted += result.getDiscounts().stream().mapToDouble(Discount::getAmount).sum();
            gifts.addAll(result.getGifts());
        }

        return total - discounted;
    }
}