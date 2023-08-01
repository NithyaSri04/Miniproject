package driverhiring;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Driver Hiring Application!");

        // Sample lists to hold data
        List<User> users = new ArrayList<>();
        List<Driver> drivers = new ArrayList<>();
        List<Vehicle> vehicles = new ArrayList<>();
        List<Booking> bookings = new ArrayList<>();
        List<LocationUpdate> locationUpdates = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Add User");
            System.out.println("2. Add Driver");
            System.out.println("3. Add Vehicle");
            System.out.println("4. Add Booking");
            System.out.println("5. Add Location Update");
            System.out.println("6. Cancel Booking");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character left after reading the integer choice

            switch (choice) {
                case 1:
                    // Add User
                    User newUser = addUser(scanner);
                    insertUser(newUser);
                    users.add(newUser);
                    break;
                case 2:
                    // Add Driver
                    Driver newDriver = addDriver(scanner);
                    insertDriver(newDriver);
                    drivers.add(newDriver);
                    break;
                case 3:
                    // Add Vehicle
                    Vehicle newVehicle = addVehicle(scanner, drivers);
                    insertVehicle(newVehicle);
                    vehicles.add(newVehicle);
                    break;
                case 4:
                    // Add Booking
                    Booking newBooking = addBooking(scanner, users, drivers, vehicles);
                    insertBooking(newBooking);
                    bookings.add(newBooking);
                    break;
                case 5:
                    // Add Location Update
                    LocationUpdate newLocationUpdate = addLocationUpdate(scanner, drivers);
                    insertLocationUpdate(newLocationUpdate);
                    locationUpdates.add(newLocationUpdate);
                    break;
                case 6:
                    // Cancel Booking
                    cancelBooking(bookings, scanner);
                    break;
                case 7:
                    System.out.println("Thank you for using the Driver Hiring Application!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 7);

        scanner.close();
    }

    private static User addUser(Scanner scanner) {
        // Implementation for adding a new user goes here
        // For simplicity, let's ask the user to input the user details
        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character left after reading the integer
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();

        return new User(userId, name, email, password, phoneNumber);
    }

    private static Driver addDriver(Scanner scanner) {
        // Implementation for adding a new driver goes here
        // For simplicity, let's ask the user to input the driver details
        System.out.print("Enter Driver ID: ");
        int driverId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character left after reading the integer
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        // Correctly handle the input for experience and average rating
        int experience;
        double averageRating;
        try {
            System.out.print("Enter Experience: ");
            experience = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character left after reading the integer
            System.out.print("Enter Average Rating: ");
            averageRating = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline character left after reading the double
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Experience and Average Rating must be valid numbers.");
            return null;
        }

        return new Driver(driverId, name, experience, averageRating);
    }

    private static Vehicle addVehicle(Scanner scanner, List<Driver> drivers) {
        // Implementation for adding a new vehicle goes here
        // For simplicity, let's ask the user to input the vehicle details
        System.out.print("Enter Vehicle ID: ");
        int vehicleId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character left after reading the integer
        System.out.print("Enter Driver ID (of the assigned driver): ");
        int driverId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character left after reading the integer
        System.out.print("Enter Vehicle Type: ");
        String type = scanner.nextLine();
        System.out.print("Enter License Plate: ");
        String licensePlate = scanner.nextLine();

        // Find the corresponding driver with the given driverId
        Driver assignedDriver = null;
        for (Driver driver : drivers) {
            if (driver.getDriverId() == driverId) {
                assignedDriver = driver;
                break;
            }
        }

        if (assignedDriver == null) {
            System.out.println("Driver with ID " + driverId + " not found. Vehicle cannot be added.");
            return null;
        }

        return new Vehicle(vehicleId, assignedDriver.getDriverId(), type, licensePlate);
    }

    private static Booking addBooking(Scanner scanner, List<User> users, List<Driver> drivers, List<Vehicle> vehicles) {
        // Implementation for adding a new booking goes here
        // For simplicity, let's ask the user to input the booking details
        System.out.print("Enter Booking ID: ");
        int bookingId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character left after reading the integer

        // Ask for user ID
        System.out.println("Available Users:");
        for (User user : users) {
            System.out.println(user.getUserId() + ": " + user.getName());
        }
        System.out.print("Select User ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character left after reading the integer

        // Find the corresponding user with the given userId
        User user = null;
        for (User u : users) {
            if (u.getUserId() == userId) {
                user = u;
                break;
            }
        }

        if (user == null) {
            System.out.println("User with ID " + userId + " not found. Booking cannot be added.");
            return null;
        }

        // Ask for driver ID
        System.out.println("Available Drivers:");
        for (Driver driver : drivers) {
            System.out.println(driver.getDriverId() + ": " + driver.getName());
        }
        System.out.print("Select Driver ID: ");
        int driverId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character left after reading the integer

        // Find the corresponding driver with the given driverId
        Driver driver = null;
        for (Driver d : drivers) {
            if (d.getDriverId() == driverId) {
                driver = d;
                break;
            }
        }

        if (driver == null) {
            System.out.println("Driver with ID " + driverId + " not found. Booking cannot be added.");
            return null;
        }

        // Ask for vehicle ID
        System.out.println("Available Vehicles:");
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle.getVehicleId() + ": " + vehicle.getType() + " (Driver: " + vehicle.getDriverId() + ")");
        }
        System.out.print("Select Vehicle ID: ");
        int vehicleId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character left after reading the integer

        // Find the corresponding vehicle with the given vehicleId
        Vehicle vehicle = null;
        for (Vehicle v : vehicles) {
            if (v.getVehicleId() == vehicleId) {
                vehicle = v;
                break;
            }
        }

        if (vehicle == null) {
            System.out.println("Vehicle with ID " + vehicleId + " not found. Booking cannot be added.");
            return null;
        }

        // Ask for other booking details
        System.out.print("Enter Place: ");
        String place = scanner.nextLine();
        System.out.print("Enter Dates: ");
        String dates = scanner.nextLine();
        System.out.print("Enter Days: ");
        String days = scanner.nextLine();
        // For simplicity, let's use the current timestamp as the booking date
        Timestamp bookingDate = new Timestamp(System.currentTimeMillis());
        String status = "Active";
        String cancellationReason = null;
        BigDecimal refundAmount = null;

        return new Booking(bookingId, user.getUserId(), driver.getDriverId(), vehicle.getVehicleId(),
                place, dates, days, bookingDate, status, cancellationReason, refundAmount);
    }

    private static LocationUpdate addLocationUpdate(Scanner scanner, List<Driver> drivers) {
        // Implementation for adding a new location update goes here
        // For simplicity, let's ask the user to input the location update details
        System.out.print("Enter Location Update ID: ");
        int updateId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character left after reading the integer

        // Ask for driver ID
        System.out.println("Available Drivers:");
        for (Driver driver : drivers) {
            System.out.println(driver.getDriverId() + ": " + driver.getName());
        }
        System.out.print("Select Driver ID: ");
        int driverId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character left after reading the integer

        // Find the corresponding driver with the given driverId
        Driver driver = null;
        for (Driver d : drivers) {
            if (d.getDriverId() == driverId) {
                driver = d;
                break;
            }
        }

        if (driver == null) {
            System.out.println("Driver with ID " + driverId + " not found. Location update cannot be added.");
            return null;
        }

        // Ask for latitude, longitude, and timestamp
        System.out.print("Enter Latitude: ");
        BigDecimal latitude = scanner.nextBigDecimal();
        System.out.print("Enter Longitude: ");
        BigDecimal longitude = scanner.nextBigDecimal();
        scanner.nextLine(); // Consume the newline character left after reading the decimal
        // For simplicity, let's use the current timestamp as the location update timestamp
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        return new LocationUpdate(updateId, driver.getDriverId(), latitude, longitude, timestamp);
    }
    private static void cancelBooking(List<Booking> bookings, Scanner scanner) {
        System.out.println("\nCancel Booking:");
        System.out.print("Enter Booking ID to cancel: ");
        int bookingId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character left after reading the integer

        Booking bookingToCancel = null;
        for (Booking booking : bookings) {
            if (booking.getBookingId() == bookingId) {
                bookingToCancel = booking;
                break;
            }
        }

        if (bookingToCancel != null) {
            // Set the booking status to "Cancelled"
            bookingToCancel.setStatus("Cancelled");
            System.out.println("Booking with ID " + bookingId + " has been cancelled.");
        } else {
            System.out.println("Booking with ID " + bookingId + " not found. No booking was cancelled.");
        }
    }

    private static void insertUser(User user) {
        // Insert the user into the database
        String query = "INSERT INTO users (user_id, name, email, password, phone_number) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, user.getUserId());
            statement.setString(2, user.getName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getPhoneNumber());
            statement.executeUpdate();
            System.out.println("User added successfully to the database!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertDriver(Driver driver) {
        // Insert the driver into the database
        String query = "INSERT INTO drivers (driver_id, name, experience, average_rating) VALUES (?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, driver.getDriverId());
            statement.setString(2, driver.getName());
            statement.setInt(3, driver.getExperience());
            statement.setDouble(4, driver.getAverageRating());
            statement.executeUpdate();
            System.out.println("Driver added successfully to the database!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertVehicle(Vehicle vehicle) {
        // Insert the vehicle into the database
        String query = "INSERT INTO vehicles (vehicle_id, driver_id, type, license_plate) VALUES (?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, vehicle.getVehicleId());
            statement.setInt(2, vehicle.getDriverId());
            statement.setString(3, vehicle.getType());
            statement.setString(4, vehicle.getLicensePlate());
            statement.executeUpdate();
            System.out.println("Vehicle added successfully to the database!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //@Override
    private static void insertBooking(Booking booking) {
        // Insert the booking into the database
        String query = "INSERT INTO bookings (booking_id, user_id, driver_id, vehicle_id, place, dates, days, " +
                "booking_date, status, cancellation_reason, refund_amount) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, booking.getBookingId());
            statement.setInt(2, booking.getUserId());
            statement.setInt(3, booking.getDriverId());
            statement.setInt(4, booking.getVehicleId());
            statement.setString(5, booking.getPlace());
            statement.setString(6, booking.getDates());
            statement.setString(7, booking.getDays());
            statement.setTimestamp(8, booking.getBookingDate());
            statement.setString(9, booking.getStatus());
            statement.setString(10, booking.getCancellationReason());
            statement.setBigDecimal(11, booking.getRefundAmount());
            statement.executeUpdate();
            System.out.println("Booking added successfully to the database!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // @Override
    private static void insertLocationUpdate(LocationUpdate locationUpdate) {
        // Insert the location update into the database
        String query = "INSERT INTO locationupdates (update_id, driver_id, latitude, longitude, timestamp) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, locationUpdate.getUpdateId());
            statement.setInt(2, locationUpdate.getDriverId());
            statement.setBigDecimal(3, locationUpdate.getLatitude());
            statement.setBigDecimal(4, locationUpdate.getLongitude());
            statement.setTimestamp(5, locationUpdate.getTimestamp());
            statement.executeUpdate();
            System.out.println("Location Update added successfully to the database!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() throws SQLException {
    	// Code for creating a database connection (abstraction)
        String url = "jdbc:mysql://localhost:3306/miniproject"; // Modify URL as needed
        String user = "root"; // Replace with your MySQL username
        String password = "01@Jekook"; // Replace with your MySQL password
        return DriverManager.getConnection(url, user, password);
    }
}
