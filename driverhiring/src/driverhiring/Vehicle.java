package driverhiring;

public class Vehicle {  // Instance variables to store vehicle data (encapsulation)
    private int vehicleId;
    private int driverId;
    private String type;
    private String licensePlate;

    // Constructor
    public Vehicle(int vehicleId, int driverId, String type, String licensePlate) {
        this.vehicleId = vehicleId;
        this.driverId = driverId;
        this.type = type;
        this.licensePlate = licensePlate;
    }

    // Getters and Setters // Getter and setter methods for accessing and modifying fields (encapsulation)
    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId=" + vehicleId +
                ", driverId=" + driverId +
                ", type='" + type + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                '}';
    }
}

