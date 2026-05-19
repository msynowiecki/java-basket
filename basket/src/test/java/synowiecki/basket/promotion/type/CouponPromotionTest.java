package synowiecki.basket.promotion.type;

import java.util.List;

import org.junit.jupiter.api.Test;

import synowiecki.basket.model.Cart;
import synowiecki.basket.model.Product;
import synowiecki.basket.promotion.PromotionResult;

import static org.junit.jupiter.api.Assertions.*;

class CouponPromotionTest {

    private final CouponPromotion promotion = new CouponPromotion("ABC", 0.3, "ABC");

    @Test
    void shouldApplyCouponToMatchingProductCode() {
        PromotionResult result = promotion.apply(List.of(
            new Product("ABC", "Couponed", 200),
            new Product("P2", "Regular", 100)
        ), List.of());

        assertEquals(1, result.getDiscounts().size());
        assertEquals(60, result.getDiscounts().get(0).getAmount(), 1e-9);
    }

    @Test
    void shouldNotDiscountNonMatchingProducts() {
        PromotionResult result = promotion.apply(List.of(
            new Product("P1", "A", 200),
            new Product("P2", "B", 100)
        ), List.of());

        assertTrue(result.getDiscounts().isEmpty());
        assertTrue(result.getGifts().isEmpty());
    }

    @Test
    void shouldDistributeMultipleCouponsAcrossMatchingProductsInCart() {
        Cart cart = new Cart();
        Product first = new Product("ABC", "Product A", 200);
        Product second = new Product("ABC", "Product B", 150);

        cart.addProduct(first);
        cart.addProduct(second);
        cart.addPromotion(new CouponPromotion("ABC", 0.3, "ABC30"));
        cart.addPromotion(new CouponPromotion("ABC", 0.2, "ABC20"));

        PromotionResult result = cart.applyPromotions();

        assertEquals(2, result.getDiscounts().size());
        assertEquals(60, result.getDiscounts().get(0).getAmount(), 1e-9);
        assertEquals(30, result.getDiscounts().get(1).getAmount(), 1e-9);
        assertEquals(List.of(first, second), result.getUsed());
    }

    @Test
    void shouldNotApplyMultipleCouponsToTheSameProduct() {
        Cart cart = new Cart();
        Product item = new Product("ABC", "Couponed", 200);

        cart.addProduct(item);
        cart.addPromotion(new CouponPromotion("ABC", 0.3, "ABC30"));
        cart.addPromotion(new CouponPromotion("ABC", 0.2, "ABC20"));

        PromotionResult result = cart.applyPromotions();

        assertEquals(1, result.getDiscounts().size());
        assertEquals(60, result.getDiscounts().get(0).getAmount(), 1e-9);
        assertEquals(List.of(item), result.getUsed());
    }
}
