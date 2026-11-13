
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int choice;

        do {

            System.out.println("\n==================================");
            System.out.println("   TRAIN TICKET MANAGEMENT SYSTEM");
            System.out.println("==================================");

            System.out.println("1. View All Trains");
            System.out.println("2. Search Train");
            System.out.println("3. Book Ticket");
            System.out.println("4. View Bookings");
            System.out.println("5. Cancel Ticket");
            System.out.println("6. Exit");

            System.out.print("\nEnter Your Choice: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {

                case 1:
                    Train.viewTrains();
                    break;

                case 2:
                    Train.searchTrains(sc);
                    break;

                case 3:
                    Train.bookTicket(sc);
                    break;

                case 4:
                    Train.viewBookings();
                    break;

                case 5:
                    Train.cancelBooking(sc);
                    break;

                case 6:
                    System.out.println("Thank You for Using Train Management System!");
                    break;

                default:
                    System.out.println("Invalid Choice! Please Try Again.");
            }

        } while (choice != 6);

        sc.close();
    }
}

