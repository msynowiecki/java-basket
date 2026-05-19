package synowiecki.basket.promotion.type;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import synowiecki.basket.model.Discount;
import synowiecki.basket.model.Product;
import synowiecki.basket.promotion.Promotion;
import synowiecki.basket.promotion.PromotionResult;


public class GetMorePromotion implements Promotion {

    private final String code;
    private final int buy;
    private final int free;

    public GetMorePromotion(String code, int buy, int free) {
        this.code = Objects.requireNonNull(code, "Product code cannot be null");
        if (code.isBlank()) {
            throw new IllegalArgumentException("Product code cannot be blank");
        }
        if (buy < 1) {
            throw new IllegalArgumentException("Buy quantity must be at least 1");
        }
        if (free < 1) {
            throw new IllegalArgumentException("Free quantity must be at least 1");
        }
        this.buy = buy;
        this.free = free;
    }

    @Override
    public PromotionResult apply(List<Product> products, List<Product> used) {

        List<Product> valid = products.stream()
            .filter(Objects::nonNull)
            .filter(product -> code.equals(product.getCode()))
            .filter(product -> !used.contains(product))
            .toList();

        if (valid.size() < buy + free) {
            return new PromotionResult(List.of(), List.of(), List.of());
        }

        List<Product> applied = valid.subList(0, buy + free);
        double discount = free * applied.get(0).getPrice();

        return new PromotionResult(
            List.of(new Discount(code + " " + buy + "+" + free, discount)),
            List.of(),
            List.copyOf(applied)
        );
    }
}
