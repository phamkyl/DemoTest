class Truck extends Vehicle {
    int payloadCapacity;

    public Truck(String manufacturer, int year, double price, String color, int payloadCapacity) {
        super(manufacturer, year, price, color);
        this.payloadCapacity = payloadCapacity;
    }

    @Override
    public String toString() {
        return super.toString() + " - Payload Capacity: " + payloadCapacity + " tons";
    }
}