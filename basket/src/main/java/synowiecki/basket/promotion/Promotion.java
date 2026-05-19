package synowiecki.basket.promotion;

import java.util.List;

import synowiecki.basket.model.Product;


public interface Promotion {
    PromotionResult apply(List<Product> products, List<Product> used);
}
