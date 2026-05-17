package synowiecki.basket.model;


public class Discount {

    private final String name;
    private final double amount;

    public Discount(String name, double amount) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Discount name cannot be null or blank");
        }

        if (amount < 0) {
            throw new IllegalArgumentException("Discount amount cannot be negative");
        }

        this.name = name;
        this.amount = amount;
    }

    public String getName() { return name; }
    public double getAmount() { return amount; }
}
