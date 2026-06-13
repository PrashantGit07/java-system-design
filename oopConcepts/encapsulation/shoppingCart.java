// Problem: Build a ShoppingCart class that manages items, supports a one-time discount code, and prevents modifications after checkout.

// Requirements:

// Private map/dictionary of items (item name to price)
// Private discount code (can only be applied once)
// Private isCheckedOut flag
// addItem(name, price): adds an item, but only if the cart hasn't been checked out
// applyDiscount(code): if the code is "SAVE10" and no discount has been applied yet, marks the discount as applied and stores it. Returns success/failure.
// getTotal(): returns the sum of all prices, minus 10% if a discount was applied
// checkout(): marks the cart as checked out if it has at least one item. After checkout, no items can be added and no discounts can be applied.

import java.util.HashMap;
import java.util.Map;

class manageCart {
    private Map<String, Double> items = new HashMap<String, Double>();
    private Boolean isCheckout = false;
    private Boolean isDiscountApplied = false;

    public void addToCart(String name, double price) {
        if (isCheckout) {
            System.out.println("can not add items in carts after checkout");
        } else {
            items.put(name, price);
            System.out.println("Added: " + name + " - $" + price);
        }
    }

    public Boolean applyDiscount(String code) {

        if (isCheckout) {
            System.out.println("Can not apply discount after checkout");
            return false;
        }

        if (code == "SAVE10" && !isDiscountApplied) {
            isDiscountApplied = true;
            System.out.println("Discount applied!");
            return true;
        } else {
            System.out.println("Invalid code or discount already applied");
            return isDiscountApplied = false;
        }
    }

    public double getTotal() {
        double sum = 0;
        for (double price : items.values()) {
            sum += price;
        }

        if (isDiscountApplied) {
            sum = sum * 0.9;
        }
        System.out.println("Total price -> " + sum);
        return sum;
    }

    public Boolean checkOut() {
        if (items.isEmpty()) {
            System.out.println("nothing to checkout");
            return false;
        }

        if (!isCheckout) {
            isCheckout = true;
            System.out.println("items checked out");
            return true;
        }

        return false;
    }

}

public class shoppingCart {
    public static void main(String[] args) {
        manageCart m = new manageCart();

        m.addToCart("Football", 133);
        m.addToCart("Bat", 500);

        System.out.println("Total before discount: $" + m.getTotal());

        m.applyDiscount("SAVE10");
        System.out.println("Total after discount: $" + m.getTotal());

        m.checkOut();

        m.addToCart("Gloves", 50);

        m.applyDiscount("SAVE10");
    }
}
