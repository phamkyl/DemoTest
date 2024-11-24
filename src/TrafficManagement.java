import java.util.ArrayList;
import java.util.List;

class TrafficManagement {
    private List<Vehicle> vehicles = new ArrayList<>();

    // Thêm xe vào danh sách
    public void registerVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    // Tìm kiếm xe theo hãng sản xuất
    public List<Vehicle> searchByManufacturer(String manufacturer) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.manufacturer.equalsIgnoreCase(manufacturer)) {
                result.add(vehicle);
            }
        }
        return result;
    }

    // Xóa xe theo hãng sản xuất
    public void removeVehicleByManufacturer(String manufacturer) {
        vehicles.removeIf(vehicle -> vehicle.manufacturer.equalsIgnoreCase(manufacturer));
    }

    // Lấy tất cả xe
    public List<Vehicle> getAllVehicles() {
        return vehicles;
    }
}