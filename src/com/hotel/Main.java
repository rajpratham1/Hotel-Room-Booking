package com.hotel;

import com.hotel.model.Booking;
import com.hotel.model.Customer;
import com.hotel.model.Room;
import com.hotel.service.HotelService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static HotelService hotelService = new HotelService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewAllRooms();
                    break;
                case 2:
                    searchAvailableRooms();
                    break;
                case 3:
                    makeBooking();
                    break;
                case 4:
                    viewAllBookings();
                    break;
                case 5:
                    cancelBooking();
                    break;
                case 0:
                    System.out.println("Thank you for using the Hotel Booking System. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n--- Hotel Booking Menu ---");
        System.out.println("1. View All Rooms");
        System.out.println("2. Search for Available Rooms");
        System.out.println("3. Make a Booking");
        System.out.println("4. View All Bookings");
        System.out.println("5. Cancel a Booking");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void viewAllRooms() {
        System.out.println("\n--- All Rooms ---");
        List<Room> rooms = hotelService.getAllRooms();
        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    private static void searchAvailableRooms() {
        try {
            System.out.print("Enter check-in date (YYYY-MM-DD): ");
            LocalDate checkIn = LocalDate.parse(scanner.nextLine());
            System.out.print("Enter check-out date (YYYY-MM-DD): ");
            LocalDate checkOut = LocalDate.parse(scanner.nextLine());

            if (checkOut.isBefore(checkIn)) {
                System.out.println("Error: Check-out date must be after check-in date.");
                return;
            }

            System.out.println("\n--- Available Rooms ---");
            List<Room> availableRooms = hotelService.findAvailableRooms(checkIn, checkOut);
            if (availableRooms.isEmpty()) {
                System.out.println("No rooms available for the selected dates.");
            } else {
                for (Room room : availableRooms) {
                    System.out.println(room);
                }
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
        }
    }

    private static void makeBooking() {
        try {
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            System.out.print("Enter your email: ");
            String email = scanner.nextLine();
            Customer customer = new Customer(name, email);

            System.out.print("Enter room number to book: ");
            int roomNumber = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Enter check-in date (YYYY-MM-DD): ");
            LocalDate checkIn = LocalDate.parse(scanner.nextLine());
            System.out.print("Enter check-out date (YYYY-MM-DD): ");
            LocalDate checkOut = LocalDate.parse(scanner.nextLine());

            if (checkOut.isBefore(checkIn)) {
                System.out.println("Error: Check-out date must be after check-in date.");
                return;
            }

            Booking booking = hotelService.makeBooking(customer, roomNumber, checkIn, checkOut);

            if (booking != null) {
                System.out.println("\nBooking successful!");
                System.out.println(booking);
            } else {
                System.out.println("\nBooking failed. The room is not available for the selected dates or does not exist.");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
        }
    }

    private static void viewAllBookings() {
        System.out.println("\n--- All Bookings ---");
        List<Booking> bookings = hotelService.getAllBookings();
        if (bookings.isEmpty()) {
            System.out.println("No bookings have been made yet.");
        } else {
            for (Booking booking : bookings) {
                System.out.println(booking);
                System.out.println("---");
            }
        }
    }

    private static void cancelBooking() {
        System.out.print("Enter booking ID to cancel: ");
        String bookingId = scanner.nextLine();

        if (hotelService.cancelBooking(bookingId)) {
            System.out.println("Booking " + bookingId + " cancelled successfully.");
        } else {
            System.out.println("Could not find booking with ID: " + bookingId);
        }
    }
}
