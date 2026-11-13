
import java.sql.*;
import java.util.Scanner;

public class Train {

    // 1. View all trains
    public static void viewTrains() {

        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM trains";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            System.out.println("\n----- AVAILABLE TRAINS -----");

            while (rs.next()) {
                System.out.println("Train ID: " + rs.getInt("train_id"));
                System.out.println("Train Name: " + rs.getString("train_name"));
                System.out.println("Source: " + rs.getString("source"));
                System.out.println("Destination: " + rs.getString("destination"));
                System.out.println("Available Seats: " + rs.getInt("available_seats"));
                System.out.println("Fare: " + rs.getDouble("fare"));
                System.out.println("----------------------------");
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // 2. Search trains
    public static void searchTrains(Scanner sc) {

        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter Source: ");
            String source = sc.nextLine();

            System.out.print("Enter Destination: ");
            String destination = sc.nextLine();

            String query = "SELECT * FROM trains WHERE source=? AND destination=?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, source);
            ps.setString(2, destination);

            ResultSet rs = ps.executeQuery();

            boolean found = false;

            while (rs.next()) {
                found = true;

                System.out.println("\nTrain ID: " + rs.getInt("train_id"));
                System.out.println("Train Name: " + rs.getString("train_name"));
                System.out.println("Fare: " + rs.getDouble("fare"));
                System.out.println("Available Seats: " + rs.getInt("available_seats"));
            }

            if (!found) {
                System.out.println("No train found!");
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // 3. Book ticket
    public static void bookTicket(Scanner sc) {

        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter Train ID: ");
            int trainId = Integer.parseInt(sc.nextLine());

            System.out.print("Enter Passenger Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Age: ");
            int age = Integer.parseInt(sc.nextLine());

            System.out.print("Enter Number of Seats: ");
            int seats = Integer.parseInt(sc.nextLine());

            String query = "SELECT * FROM trains WHERE train_id=?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, trainId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                int available = rs.getInt("available_seats");
                double fare = rs.getDouble("fare");

                if (seats > 0 && seats <= available && age > 0 && !name.trim().isEmpty()) {

                    double totalFare = fare * seats;

                    String update = "UPDATE trains SET available_seats=available_seats-? WHERE train_id=?";
                    PreparedStatement ps1 = con.prepareStatement(update);

                    ps1.setInt(1, seats);
                    ps1.setInt(2, trainId);
                    ps1.executeUpdate();

                    String insert = "INSERT INTO bookings(passenger_name,age,train_id,seats,total_fare) VALUES(?,?,?,?,?)";
                    PreparedStatement ps2 = con.prepareStatement(insert);

                    ps2.setString(1, name);
                    ps2.setInt(2, age);
                    ps2.setInt(3, trainId);
                    ps2.setInt(4, seats);
                    ps2.setDouble(5, totalFare);

                    ps2.executeUpdate();

                    System.out.println("Ticket Booked Successfully!");
                    System.out.println("Total Fare: Rs. " + totalFare);

                } else {
                    System.out.println("Invalid details or seats not available!");
                }

            } else {
                System.out.println("Train ID not found!");
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // 4. View bookings
    public static void viewBookings() {

        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM bookings";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            System.out.println("\n----- BOOKING DETAILS -----");

            while (rs.next()) {
                System.out.println("Booking ID: " + rs.getInt("booking_id"));
                System.out.println("Passenger Name: " + rs.getString("passenger_name"));
                System.out.println("Train ID: " + rs.getInt("train_id"));
                System.out.println("Seats: " + rs.getInt("seats"));
                System.out.println("Total Fare: " + rs.getDouble("total_fare"));
                System.out.println("---------------------------");
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // 5. Cancel booking
    public static void cancelBooking(Scanner sc) {

        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter Booking ID: ");
            int bookingId = Integer.parseInt(sc.nextLine());

            String query = "SELECT * FROM bookings WHERE booking_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, bookingId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                int trainId = rs.getInt("train_id");
                int seats = rs.getInt("seats");

                String delete = "DELETE FROM bookings WHERE booking_id=?";
                PreparedStatement ps1 = con.prepareStatement(delete);

                ps1.setInt(1, bookingId);
                ps1.executeUpdate();

                String update = "UPDATE trains SET available_seats=available_seats+? WHERE train_id=?";
                PreparedStatement ps2 = con.prepareStatement(update);

                ps2.setInt(1, seats);
                ps2.setInt(2, trainId);
                ps2.executeUpdate();

                System.out.println("Ticket Cancelled Successfully!");

            } else {
                System.out.println("Booking ID not found!");
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}


