package synowiecki.basket.model;

import java.util.List;

import org.junit.jupiter.api.Test;

import synowiecki.basket.promotion.PromotionResult;
import synowiecki.basket.promotion.type.CouponPromotion;
import synowiecki.basket.promotion.type.FreeGiftPromotion;
import synowiecki.basket.promotion.type.PercentagePromotion;

import static org.junit.jupiter.api.Assertions.*;


class CartTest {

    @Test
    void shouldAddAndRemoveProducts() {
        Cart cart = new Cart();
        Product product = new Product("P1", "Laptop", 3000);

        cart.addProduct(product);
        assertEquals(1, cart.getProducts().size());

        cart.removeProduct(product);
        assertTrue(cart.getProducts().isEmpty());
    }

    @Test
    void shouldFailWhenAddingNullProduct() {
        Cart cart = new Cart();
        assertThrows(NullPointerException.class, () -> cart.addProduct(null));
    }

    @Test
    void shouldReturnUnmodifiableProductsList() {
        Cart cart = new Cart();
        cart.addProduct(new Product("P1", "Laptop", 3000));

        assertThrows(UnsupportedOperationException.class, () -> cart.getProducts().add(new Product("P2", "Mouse", 100)));
    }

    @Test
    void shouldApplyPromotionsAndCalculateTotal() {
        Cart cart = new Cart();
        cart.addProduct(new Product("ABC", "Laptop", 200));
        cart.addProduct(new Product("P2", "Mouse", 150));

        cart.addPromotion(new PercentagePromotion(300, 0.05));
        cart.addPromotion(new FreeGiftPromotion(200, "Mug"));
        cart.addPromotion(new CouponPromotion("ABC", 0.3));

        PromotionResult result = cart.applyPromotions();

        assertEquals(2, result.getDiscounts().size());
        assertEquals(List.of("Mug"), result.getGifts());
        assertEquals(350 - 17.5 - 60, cart.calculateTotal(), 1e-9);
    }

    @Test
    void shouldAllowRemovingPromotions() {
        Cart cart = new Cart();
        PercentagePromotion percentage = new PercentagePromotion(300, 0.05);

        cart.addPromotion(percentage);
        cart.removePromotion(percentage);

        assertTrue(cart.getPromotions().isEmpty());
    }
}
