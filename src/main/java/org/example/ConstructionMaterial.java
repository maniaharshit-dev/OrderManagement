package org.example;

public abstract class ConstructionMaterial {
    private String id;
    private String name;
    private double price;
    private int stock;

    public ConstructionMaterial(String id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void reduceStock(int quantity) {
        if (stock >= quantity) {
            stock -= quantity;
        } else {
            throw new IllegalArgumentException("Not enough stock for " + name);
        }
    }

    public void displayInfo() {
        System.out.printf("ID: %s | Name: %s | Price: $%.2f | Stock: %d\n", id, name, price, stock);
    }
}