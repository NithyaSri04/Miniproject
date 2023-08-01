package driverhiring;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Booking {  // Instance variables to store booking data (encapsulation)
    private int bookingId;
    private int userId;
    private int driverId;
    private int vehicleId;
    private String place;
    private String dates;
    private String days;
    private Timestamp bookingDate;
    private String status;
    private String cancellationReason;
    private BigDecimal refundAmount;

    // Constructor
    public Booking(int bookingId, int userId, int driverId, int vehicleId, String place, String dates,
                   String days, Timestamp bookingDate, String status, String cancellationReason,
                   BigDecimal refundAmount) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.driverId = driverId;
        this.vehicleId = vehicleId;
        this.place = place;
        this.dates = dates;
        this.days = days;
        this.bookingDate = bookingDate;
        this.status = status;
        this.cancellationReason = cancellationReason;
        this.refundAmount = refundAmount;
    }

    // Getters and Setters  // Getter and setter methods for accessing and modifying fields (encapsulation)
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public Timestamp getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Timestamp bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", userId=" + userId +
                ", driverId=" + driverId +
                ", vehicleId=" + vehicleId +
                ", place='" + place + '\'' +
                ", dates='" + dates + '\'' +
                ", days='" + days + '\'' +
                ", bookingDate=" + bookingDate +
                ", status='" + status + '\'' +
                ", cancellationReason='" + cancellationReason + '\'' +
                ", refundAmount=" + refundAmount +
                '}';
    }
}
