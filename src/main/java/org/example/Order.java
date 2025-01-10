package org.example;
import java.util.List;
import java.util.ArrayList;

public class Order {

//  Could use a pair class to couple each product/sku with a quantity, thereafter we'd not need to access to through
//  indices, and use the more concise forEach construct
    private List<ConstructionMaterial> productList;
    private List<Integer> quantities;
    private double totalAmount;

    public Order() {

//      The two arrays below are coupled with indices
        productList = new ArrayList<>();
        quantities = new ArrayList<>();
        totalAmount = 0.0;
    }

    public void addProduct(ConstructionMaterial product, int quantity) {
        if (product.getStock() < quantity) {
            System.out.println("Insufficient stock for " + product.getName());
            return;
        }
        product.reduceStock(quantity);
        productList.add(product);
        quantities.add(quantity);

        // Calculate discount based on quantity thresholds
        double productPrice = product.getPrice();
        double discount = 0.0;

//      Fetch the discount percentages from a constants file. Make a constants file.
        if (quantity > 100) {
            discount = 0.05; // 5% discount
        } else if (quantity > 20) {
            discount = 0.02; // 2% discount
        } else if (quantity > 10) {
            discount = 0.01; // 1% discount
        }

        double discountedPrice = productPrice * (1 - discount);
        totalAmount += discountedPrice * quantity;

        // Inform the user about the discount applied
        System.out.printf("%d units of %s added to the order with %.0f%% discount applied. Price per unit: $%.2f\n",
                quantity, product.getName(), discount * 100, discountedPrice);
    }

    public void applyDiscount(DISCOUNTABLE discount) {
        totalAmount = discount.applyDiscount(totalAmount);
    }

    public void displayInvoice() {
        System.out.println("\n----- Final Invoice -----");
        System.out.println("Product Name\tQuantity\tPrice");

        for (int i = 0; i < productList.size(); i++) {
//          Index is required to correctly access the two quantities
            ConstructionMaterial product = productList.get(i);
            int quantity = quantities.get(i);
            System.out.printf("%-15s %-10d $%.2f\n", product.getName(), quantity, product.getPrice() * quantity);
        }
        System.out.printf("Total Amount: $%.2f\n", totalAmount);

        // Display updated stock quantities
        System.out.println("\n----- Updated Stock Quantities -----");
        for (ConstructionMaterial product : productList) {
            System.out.printf("%s: %d units remaining\n", product.getName(), product.getStock());
        }
    }
}
