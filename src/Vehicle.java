class Vehicle {
    String manufacturer;
    int year;
    double price;
    String color;

    public Vehicle(String manufacturer, int year, double price, String color) {
        this.manufacturer = manufacturer;
        this.year = year;
        this.price = price;
        this.color = color;
    }

    @Override
    public String toString() {
        return manufacturer + " - " + year + " - " + price + " - " + color;
    }
}