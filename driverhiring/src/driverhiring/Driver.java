package driverhiring;

public class Driver {  // Instance variables to store driver data (encapsulation)
    private int driverId;
    private String name;
    private int experience;
    private double averageRating;

    // Constructor
    public Driver(int driverId, String name, int experience, double averageRating) {
        this.driverId = driverId;
        this.name = name;
        this.experience = experience;
        this.averageRating = averageRating;
    }

    // Getters and Setters  // Getter and setter methods for accessing and modifying fields (encapsulation)
    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "driverId=" + driverId +
                ", name='" + name + '\'' +
                ", experience=" + experience +
                ", averageRating=" + averageRating +
                '}';
    }
}

