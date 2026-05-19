package synowiecki.basket;

import java.util.Comparator;

import synowiecki.basket.model.Cart;
import synowiecki.basket.model.Discount;
import synowiecki.basket.model.Product;
import synowiecki.basket.promotion.PromotionResult;
import synowiecki.basket.promotion.type.CouponPromotion;
import synowiecki.basket.promotion.type.FreeGiftPromotion;
import synowiecki.basket.promotion.type.GetMorePromotion;
import synowiecki.basket.promotion.type.PercentagePromotion;

public class Main {

    public static void main(String[] args) {
        Cart cart = new Cart();

        Product laptop = new Product("LAP", "Laptop", 1200);
        Product mouse = new Product("MSE", "Wireless Mouse", 50);
        Product mug = new Product("MUG", "Coffee Mug", 15);
        Product bag = new Product("BAG", "Backpack", 70);
        Product headset = new Product("HED", "Headset", 90);

        cart.addProduct(laptop);
        cart.addProduct(mouse);
        cart.addProduct(mug);
        cart.addProduct(bag);
        cart.addProduct(headset);

        cart.addPromotion(new PercentagePromotion(300, 0.10));
        cart.addPromotion(new FreeGiftPromotion(250, new Product("GFT", "Sticker Pack", 0)));
        cart.addPromotion(new CouponPromotion("MSE", 0.50, "HALFOFF"));
        cart.addPromotion(new GetMorePromotion(2, 1));

        System.out.println("=== Shopping Cart Example ===");
        System.out.println("Products in cart:");
        printProducts(cart);

        System.out.println("\nProducts sorted by price ascending:");
        cart.getProducts().stream()
            .sorted(Comparator.comparingDouble(Product::getPrice))
            .forEach(product -> System.out.printf(" - %s (%s): $%.2f%n", product.getName(), product.getCode(), product.getPrice()));

        System.out.println("\nProducts sorted by name alphabetically:");
        cart.getProducts().stream()
            .sorted(Comparator.comparing(Product::getName))
            .forEach(product -> System.out.printf(" - %s (%s): $%.2f%n", product.getName(), product.getCode(), product.getPrice()));

        PromotionResult result = cart.applyPromotions();

        System.out.println("\nApplied promotions:");
        if (result.getDiscounts().isEmpty()) {
            System.out.println(" - No discounts applied");
        } else {
            for (Discount discount : result.getDiscounts()) {
                System.out.printf(" - %s: -$%.2f%n", discount.getName(), discount.getAmount());
            }
        }

        System.out.println("Gifts received:");
        if (result.getGifts().isEmpty()) {
            System.out.println(" - None");
        } else {
            result.getGifts().forEach(gift -> System.out.printf(" - %s (%s)%n", gift.getName(), gift.getCode()));
        }
        System.out.printf("Total before promotions: $%.2f%n", cart.getProducts().stream().mapToDouble(Product::getPrice).sum());
        System.out.printf("Total after promotions:  $%.2f%n", cart.calculateTotal());
    }

    private static void printProducts(Cart cart) {
        cart.getProducts().forEach(product ->
            System.out.printf(" - %s (%s): $%.2f%n", product.getName(), product.getCode(), product.getPrice())
        );
    }
}
