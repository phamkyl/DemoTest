class Car extends Vehicle {
    int seats;
    String engineType;

    public Car(String manufacturer, int year, double price, String color, int seats, String engineType) {
        super(manufacturer, year, price, color);
        this.seats = seats;
        this.engineType = engineType;
    }

    @Override
    public String toString() {
        return super.toString() + " - Seats: " + seats + " - Engine: " + engineType;
    }
}