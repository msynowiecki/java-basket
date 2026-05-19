package synowiecki.basket.model;


public final class Product {

    private final String code;
    private final String name;
    private final double price;

    public Product(String code, String name, double price) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be null or blank");
        }

        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Product code cannot be null or blank");
        }

        if (price < 0) {
            throw new IllegalArgumentException("Product price cannot be negative");
        }

        this.code = code;
        this.name = name;
        this.price = price;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Product product = (Product) object;

        return Double.compare(product.price, price) == 0
            && code.equals(product.code)
            && name.equals(product.name);
    }

    @Override
    public int hashCode() {
        int result = code.hashCode();
        result = 31 * result + name.hashCode();

        long temporary = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temporary ^ (temporary >>> 32));
        
        return result;
    }
}
