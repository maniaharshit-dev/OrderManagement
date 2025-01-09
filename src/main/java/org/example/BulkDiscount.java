package org.example;

public class BulkDiscount implements DISCOUNTABLE {
    private final double discountRate; // e.g., 0.20 for a 20% discount
    private final int minimumQuantity; // Minimum quantity required to apply the discount

    public BulkDiscount(double discountRate, int minimumQuantity) {
        this.discountRate = discountRate;
        this.minimumQuantity = minimumQuantity;
    }

    @Override
    public double applyDiscount(double price) {
        // This method will need to be modified to account for quantity in some way.
        // For now, we will assume a fixed quantity for demonstration.
        int quantity = 1000; // Example fixed quantity
        if (quantity >= minimumQuantity) {
            return price * (1 - discountRate); // Apply the discount
        }
        return price; // No discount applied
    }
}
