package driverhiring;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class LocationUpdate {  // Instance variables to store location update data (encapsulation)
    private int updateId;
    private int driverId;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Timestamp timestamp;

    // Constructor
    public LocationUpdate(int updateId, int driverId, BigDecimal latitude, BigDecimal longitude, Timestamp timestamp) {
        this.updateId = updateId;
        this.driverId = driverId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
    }

    // Getters and Setters  // Getter and setter methods for accessing and modifying fields (encapsulation)
    public int getUpdateId() {
        return updateId;
    }

    public void setUpdateId(int updateId) {
        this.updateId = updateId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "LocationUpdate{" +
                "updateId=" + updateId +
                ", driverId=" + driverId +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", timestamp=" + timestamp +
                '}';
    }
}
