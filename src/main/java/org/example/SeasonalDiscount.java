package org.example;

public class SeasonalDiscount implements DISCOUNTABLE {
    private final double discountRate; // e.g., 0.10 for a 10% discount

    public SeasonalDiscount(double discountRate) {
        this.discountRate = discountRate;
    }

    @Override
    public double applyDiscount(double price) {
        return price * (1 - discountRate); // Apply the discount
    }
}
