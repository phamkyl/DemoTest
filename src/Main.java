import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Main extends JFrame {
    private JTextField manufacturerField, yearField, priceField, colorField, seatsField, engineField, capacityField;
    private JButton registerButton, removeButton, searchButton, exitButton;
    private JTable vehiclesTable;
    private TrafficManagement manager;
    private JTextField searchField; // Added search field

    public Main() {
        manager = new TrafficManagement();
        setTitle("Vehicle Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main Layout
        setLayout(new BorderLayout(10, 10));

        // Input Panel for Vehicle Registration
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(7, 2, 10, 10)); // Grid with spacing

        inputPanel.add(new JLabel("Manufacturer:"));
        manufacturerField = new JTextField();
        inputPanel.add(manufacturerField);

        inputPanel.add(new JLabel("Year:"));
        yearField = new JTextField();
        inputPanel.add(yearField);

        inputPanel.add(new JLabel("Price:"));
        priceField = new JTextField();
        inputPanel.add(priceField);

        inputPanel.add(new JLabel("Color:"));
        colorField = new JTextField();
        inputPanel.add(colorField);

        inputPanel.add(new JLabel("Seats:"));
        seatsField = new JTextField();
        inputPanel.add(seatsField);

        inputPanel.add(new JLabel("Engine Type:"));
        engineField = new JTextField();
        inputPanel.add(engineField);

        inputPanel.add(new JLabel("Payload Capacity:"));
        capacityField = new JTextField();
        inputPanel.add(capacityField);

        add(inputPanel, BorderLayout.NORTH);

        // Search Bar for Vehicle Search
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));

        searchPanel.add(new JLabel("Search by Manufacturer:"));
        searchField = new JTextField(15); // Search text field
        searchPanel.add(searchField);

        searchButton = new JButton("Search");
        searchPanel.add(searchButton);

        add(searchPanel, BorderLayout.CENTER);

        // Button Panel for Actions
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10)); // More spacious buttons

        registerButton = new JButton("Register Vehicle");
        removeButton = new JButton("Remove by Manufacturer");
        exitButton = new JButton("Exit");

        buttonPanel.add(registerButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(exitButton);

        // Add button panel to the bottom of the layout
        add(buttonPanel, BorderLayout.SOUTH);

        // Vehicle Data Table
        String[] columns = {"Manufacturer", "Year", "Price", "Color", "Seats/Capacity", "Engine/Truck Capacity"};
        vehiclesTable = new JTable(new Object[0][6], columns);
        vehiclesTable.setFillsViewportHeight(true);
        vehiclesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane tableScrollPane = new JScrollPane(vehiclesTable);
        add(tableScrollPane, BorderLayout.EAST); // Move table to the right

        // Event Listeners
        registerButton.addActionListener(e -> registerVehicle());
        removeButton.addActionListener(e -> removeVehicle());
        searchButton.addActionListener(e -> searchVehicle());
        exitButton.addActionListener(e -> System.exit(0));
    }

    private void registerVehicle() {
        try {
            String manufacturer = manufacturerField.getText();
            int year = Integer.parseInt(yearField.getText());
            double price = Double.parseDouble(priceField.getText());
            String color = colorField.getText();
            int seats = Integer.parseInt(seatsField.getText());
            String engineType = engineField.getText();
            int capacity = Integer.parseInt(capacityField.getText());

            Vehicle vehicle;
            if (capacity > 0) {
                vehicle = new Truck(manufacturer, year, price, color, capacity);
            } else {
                vehicle = new Car(manufacturer, year, price, color, seats, engineType);
            }

            manager.registerVehicle(vehicle);
            updateVehicleTable();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeVehicle() {
        String manufacturer = manufacturerField.getText();
        manager.removeVehicleByManufacturer(manufacturer);
        updateVehicleTable();
    }

    private void searchVehicle() {
        String manufacturer = searchField.getText(); // Get the input from the search field
        var vehicles = manager.searchByManufacturer(manufacturer);
        String[][] data = new String[vehicles.size()][6];

        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle v = vehicles.get(i);
            data[i] = new String[] { v.manufacturer, String.valueOf(v.year), String.valueOf(v.price), v.color,
                    (v instanceof Car ? String.valueOf(((Car) v).seats) : ""),
                    (v instanceof Truck ? String.valueOf(((Truck) v).payloadCapacity) : "") };
        }

        vehiclesTable.setModel(new javax.swing.table.DefaultTableModel(data,
                new String[] { "Manufacturer", "Year", "Price", "Color", "Seats/Capacity", "Engine/Truck Capacity" }));
    }

    private void updateVehicleTable() {
        var vehicles = manager.getAllVehicles();
        String[][] data = new String[vehicles.size()][6];

        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle v = vehicles.get(i);
            data[i] = new String[] { v.manufacturer, String.valueOf(v.year), String.valueOf(v.price), v.color,
                    (v instanceof Car ? String.valueOf(((Car) v).seats) : ""),
                    (v instanceof Truck ? String.valueOf(((Truck) v).payloadCapacity) : "") };
        }

        vehiclesTable.setModel(new javax.swing.table.DefaultTableModel(data,
                new String[] { "Manufacturer", "Year", "Price", "Color", "Seats/Capacity", "Engine/Truck Capacity" }));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main frame = new Main();
            frame.setVisible(true);
        });
    }
}
