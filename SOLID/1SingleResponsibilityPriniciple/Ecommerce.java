
//example of SRP
//below is case where on class handles everything

// import java.util.ArrayList;

// class Product {

//     public String name;
//     public int price;

//     public Product(String name, int price) {
//         this.name = name;
//         this.price = price;
//     }
// }

// class ShoppingCart {

//     ArrayList<Product> products = new ArrayList<>();

//     public void addProduct(Product p) {
//         products.add(p);
//     }

//     public void getProducts() {
//         for (Product p : products) {
//             System.out.println("Product Name -> " + p.name);
//             System.out.println("Price -> " + p.price);

//         }
//     }

//     public void calculatePrice() {
//         double total = 0;
//         for (Product p : products) {
//             total += p.price;
//         }
//         System.out.println("Total Price -> " + total);
//     }

//     public void printInvoice() {
//         for (Product p : products) {
//             System.out.println("invoice -> " + "name ->" + p.name + "price -> " + p.price);
//         }
//     }

//     public void saveToDb() {
//         System.out.println("Saving to db");
//     }
// }

// public class Ecommerce {
//     public static void main(String[] args) {

//         ShoppingCart s = new ShoppingCart();

//         Product p = new Product("Football", 3000);

//         s.addProduct(new Product("Cricket bat", 5000));
//         s.addProduct(new Product("pencil", 5000));

//         s.getProducts();
//         s.calculatePrice();
//         s.printInvoice();
//         s.saveToDb();

//     }
// }

//following proper single responsbility

import java.util.ArrayList;

class Product {
    String name;
    int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }
}

class ShoppingCart {
    ArrayList<Product> products = new ArrayList<>();

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void addProduct(Product p) {
        products.add(p);
    }

    // it will only calculate price

    public void calculatePrice() {
        double total = 0;
        for (Product product : products) {
            total += product.price;
        }

        System.out.println("Total Price of the cart -> " + total);
    }
}

class printInvoice {
    ShoppingCart s;

    public printInvoice(ShoppingCart shoppingCart) {
        this.s = shoppingCart;
    }

    public void printProdcutsInvoice() {
        System.out.println("Invoice");
        for (var p : s.getProducts()) {
            System.out.println(p.name);
            System.out.println(p.price);
        }
    }
}

class saveToDb {
    ShoppingCart shoppingCart;

    public saveToDb(ShoppingCart s) {
        this.shoppingCart = s;
    }

    public void saveProductsToDb() {
        for (var products : shoppingCart.getProducts()) {
            System.out.println("product shopping details saved to db => name => " + products.name);
        }
    }
}

public class Ecommerce {
    public static void main(String[] args) {
        ShoppingCart s = new ShoppingCart();

        s.addProduct(new Product("Pen", 12));
        s.addProduct(new Product("rubber", 10));

        s.calculatePrice();

        printInvoice p = new printInvoice(s);

        p.printProdcutsInvoice();

        saveToDb sDb = new saveToDb(s);
        sDb.saveProductsToDb();
    }
}