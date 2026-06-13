
abstract class Discount {
    protected String label;

    public Discount(String label) {
        this.label = label;
    }

    abstract double applyDiscount(double amount);

    public void describe(double originalPrice) {

        System.out.println("Label : " + label);
        System.out.println("original price -> " + originalPrice);
        System.out.println("Discounted Price -> " + applyDiscount(originalPrice));
    }
}

class PercentageDiscount extends Discount {

    private double percentage;

    public PercentageDiscount(double percentage) {
        super("Percent Discount");
        this.percentage = percentage;
    }

    @Override
    public double applyDiscount(double amout) {
        return amout * (1 - percentage / 100);
    }

}

class FlatDiscount extends Discount {
    double flatDiscountPrice;

    public FlatDiscount(double flatDiscountPrice) {
        super("Flat Discount");
        this.flatDiscountPrice = flatDiscountPrice;
    }

    @Override
    public double applyDiscount(double amount) {
        return amount - flatDiscountPrice;
    }
}

class OrderProcessor {
    public void procesOrder(String itemName, double amount, Discount discount) {
        System.out.println("Item Name -> " + itemName);
        System.out.println("Price -> " + amount);

        discount.applyDiscount(amount);
        discount.describe(amount);
    }
}

public class polymorphismPractice {
    public static void main(String[] args) {
        OrderProcessor o1 = new OrderProcessor();

        o1.procesOrder("Football", 5000, new PercentageDiscount(10));
        o1.procesOrder("Bat", 500, new FlatDiscount(35));
    }
}
