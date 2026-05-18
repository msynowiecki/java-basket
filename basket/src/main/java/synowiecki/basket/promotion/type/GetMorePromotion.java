package synowiecki.basket.promotion.type;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import synowiecki.basket.model.Discount;
import synowiecki.basket.model.Product;
import synowiecki.basket.promotion.Promotion;
import synowiecki.basket.promotion.PromotionResult;


public class GetMorePromotion implements Promotion {

    private final int buy;
    private final int free;

    public GetMorePromotion(int buy, int free) {
        this.buy = buy;
        this.free = free;
    }

    @Override
    public PromotionResult apply(List<Product> products) {

        List<Product> valid = products.stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingDouble(Product::getPrice))
                .toList();

        if (valid.size() < buy + free) {
            return new PromotionResult(List.of(), List.of());
        }

        Product freeProduct = valid.get(free - 1);

        return new PromotionResult(
                List.of(new Discount(buy + "+" + free, freeProduct.getPrice())),
                List.of()
        );
    }
}
