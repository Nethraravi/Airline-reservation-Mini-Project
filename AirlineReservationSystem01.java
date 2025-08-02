import java.util.*;

class Flight {
    private String flightNumber;
    private String airline;
    private int capacity;
    private int bookedSeats;

    public Flight(String flightNumber, String airline, int capacity) {
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.capacity = capacity;
        this.bookedSeats = 0;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getAirline() {
        return airline;
    }

    public int getAvailableSeats() {
        return capacity - bookedSeats;
    }

    public boolean bookSeats(int numSeats) {
        if (numSeats <= getAvailableSeats()) {
            bookedSeats += numSeats;
            return true;
        } else {
            return false;
        }
    }

    public int getBookedSeats() {
        return bookedSeats;
    }
}

class ReservationSystem {
    private HashMap<String, Flight> flights;

    public ReservationSystem() {
        flights = new HashMap<>();
    }

    public void addFlight(String flightNumber, String airline, int capacity) {
        flights.put(flightNumber, new Flight(flightNumber, airline, capacity));
    }

    public boolean bookSeat(String flightNumber, int numSeats) {
        Flight flight = flights.get(flightNumber);
        if (flight != null) {
            return flight.bookSeats(numSeats);
        }
        return false;
    }

    public int getAvailableSeats(String flightNumber) {
        Flight flight = flights.get(flightNumber);
        if (flight != null) {
            return flight.getAvailableSeats();
        }
        return -1;
    }

    public void displayAvailableFlights() {
        System.out.println("Available Flights:");
        for (Flight flight : flights.values()) {
            System.out.println(
                    flight.getFlightNumber() + " (" + flight.getAirline() + ") - Available Seats: "
                            + flight.getAvailableSeats());
        }
    }

    public void displayBookedSeats(String flightNumber) {
        Flight flight = flights.get(flightNumber);
        if (flight != null) {
            System.out.println("Booked Seats for Flight " + flightNumber + ": " + flight.getBookedSeats());
        } else {
            System.out.println("Flight not found.");
        }
    }
}

public class AirlineReservationSystem01 {
    public static void main(String[] args) {
        ReservationSystem reservationSystem = new ReservationSystem();
        Scanner scanner = new Scanner(System.in);

        // Add flights with separate flight number and airline name
        reservationSystem.addFlight("FL001", "AIRASIA", 100);
        reservationSystem.addFlight("FL002", "INDIGO", 150);

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. View available flights");
            System.out.println("2. Book a seat");
            System.out.println("3. View booked seats for a flight");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    reservationSystem.displayAvailableFlights();
                    break;
                case 2:
                    System.out.print("Enter flight number: ");
                    String flightNumber = scanner.nextLine().trim().toUpperCase();
                    System.out.print("Enter number of seats to book: ");
                    int numSeats = scanner.nextInt();
                    boolean booked = reservationSystem.bookSeat(flightNumber, numSeats);
                    if (booked) {
                        System.out.println("Seats booked successfully!");
                    } else {
                        System.out.println("Sorry, seats couldn't be booked.");
                    }
                    break;
                case 3:
                    System.out.print("Enter flight number to view booked seats: ");
                    String viewFlightNumber = scanner.nextLine().trim().toUpperCase();
                    reservationSystem.displayBookedSeats(viewFlightNumber);
                    break;
                case 4:
                    System.out.println("Exiting...THANK YOU!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
