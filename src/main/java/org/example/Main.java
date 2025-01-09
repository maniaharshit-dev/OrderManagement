package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create products
        Scanner scanner = new Scanner(System.in);

        // Initialize products
        List<ConstructionMaterial> materials = new ArrayList<>();
        materials.add(new ExternalConstruction("E001", "Steel", 100.00, 50));
        materials.add(new ExternalConstruction("E002", "Cement", 10.00, 100));
        materials.add(new ExternalConstruction("E003", "Bitumen", 50.00, 30));
        materials.add(new InternalConstruction("I001", "Tiles", 20.00, 200));
        materials.add(new InternalConstruction("I002", "Paint", 15.00, 150));
        materials.add(new InternalConstruction("I003", "Sanitaryware", 40.00, 100));
        materials.add(new InternalConstruction("I004", "Bath Fittings", 60.00, 80));

        // Display available products
        System.out.println("Available Construction Materials:");
        for (ConstructionMaterial material : materials) {
            material.displayInfo();
        }

        // Create an order
        Order order = new Order();

        // Allow user to add products to the order
        while (true) {
            System.out.println("\nEnter the product ID to add to your order (or type 'done' to finish): ");
            String productId = scanner.nextLine();
            if (productId.equalsIgnoreCase("done")) {
                break;
            }

            ConstructionMaterial selectedMaterial = null;
            for (ConstructionMaterial material : materials) {
                if (material.getId().equalsIgnoreCase(productId)) {
                    selectedMaterial = material;
                    break;
                }
            }

            if (selectedMaterial == null) {
                System.out.println("Invalid product ID. Try again.");
                continue;
            }
            // Display discount information
            System.out.println("Discount Information:");
            System.out.println("1% discount for more than 10 units.");
            System.out.println("2% discount for more than 20 units.");
            System.out.println("5% discount for more than 100 units.");

            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            order.addProduct(selectedMaterial, quantity);

        }

        // Create a seasonal discount of 10%
        DISCOUNTABLE seasonalDiscount = new SeasonalDiscount(0.10); // 10% discount

        // Apply the seasonal discount to the total amount
        order.applyDiscount(seasonalDiscount);

        // Display final invoice
        order.displayInvoice();

        // Close scanner
        scanner.close();
    }
}