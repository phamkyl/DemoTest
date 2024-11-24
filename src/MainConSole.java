import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainConSole {
    private static TrafficManagement manager;
    private static Scanner scanner;

    public static void main(String[] args) {
        manager = new TrafficManagement();
        scanner = new Scanner(System.in);

        System.out.println("Welcome to the Vehicle Management System");

        boolean running = true;
        while (running) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Register Vehicle");
            System.out.println("2. Remove Vehicle by Manufacturer");
            System.out.println("3. Search Vehicle by Manufacturer");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    registerVehicle();
                    break;
                case 2:
                    removeVehicle();
                    break;
                case 3:
                    searchVehicle();
                    break;
                case 4:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void registerVehicle() {
        try {
            System.out.println("\nEnter vehicle details:");

            System.out.print("Manufacturer: ");
            String manufacturer = scanner.nextLine();

            System.out.print("Year: ");
            int year = scanner.nextInt();

            System.out.print("Price: ");
            double price = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            System.out.print("Color: ");
            String color = scanner.nextLine();

            System.out.print("Seats: ");
            int seats = scanner.nextInt();

            scanner.nextLine(); // Consume newline

            System.out.print("Engine Type: ");
            String engineType = scanner.nextLine();

            System.out.print("Payload Capacity: ");
            int capacity = scanner.nextInt();

            Vehicle vehicle;
            if (capacity > 0) {
                vehicle = new Truck(manufacturer, year, price, color, capacity);
            } else {
                vehicle = new Car(manufacturer, year, price, color, seats, engineType);
            }

            manager.registerVehicle(vehicle);
            System.out.println("Vehicle registered successfully!");

        } catch (Exception ex) {
            System.out.println("Error: Please enter valid numeric values.");
            scanner.nextLine(); // Consume the invalid input
        }
    }

    private static void removeVehicle() {
        System.out.print("\nEnter the manufacturer to remove: ");
        String manufacturer = scanner.nextLine();
        manager.removeVehicleByManufacturer(manufacturer);
        System.out.println("Vehicle removed successfully (if found).");
    }

    private static void searchVehicle() {
        System.out.print("\nEnter manufacturer to search: ");
        String manufacturer = scanner.nextLine();

        List<Vehicle> vehicles = manager.searchByManufacturer(manufacturer);
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found for the manufacturer: " + manufacturer);
        } else {
            System.out.println("Search Results:");
            for (Vehicle v : vehicles) {
                System.out.println(v);
            }
        }
    }
}
