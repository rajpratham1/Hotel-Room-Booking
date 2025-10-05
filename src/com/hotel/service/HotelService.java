package com.hotel.service;

import com.hotel.model.Booking;
import com.hotel.model.Customer;
import com.hotel.model.Room;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HotelService {
    private static final String ROOMS_FILE = "data/rooms.csv";
    private static final String BOOKINGS_FILE = "data/bookings.csv";
    private List<Room> rooms = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();
    private int bookingIdCounter = 1;

    public HotelService() {
        loadRooms();
        loadBookings();
    }

    private void loadRooms() {
        if (Files.exists(Paths.get(ROOMS_FILE))) {
            try (BufferedReader reader = new BufferedReader(new FileReader(ROOMS_FILE))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    Room room = new Room(Integer.parseInt(data[0]), data[1], Double.parseDouble(data[2]));
                    rooms.add(room);
                }
            } catch (IOException e) {
                System.err.println("Error loading rooms: " + e.getMessage());
            }
        } else {
            // Initialize some rooms if the file doesn't exist
            rooms.add(new Room(101, "Single", 100));
            rooms.add(new Room(102, "Single", 100));
            rooms.add(new Room(201, "Double", 150));
            rooms.add(new Room(202, "Double", 150));
            rooms.add(new Room(301, "Suite", 250));
            saveRooms();
        }
    }

    private void loadBookings() {
        if (Files.exists(Paths.get(BOOKINGS_FILE))) {
            try (BufferedReader reader = new BufferedReader(new FileReader(BOOKINGS_FILE))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    String bookingId = data[0];
                    Customer customer = new Customer(data[1], data[2]);
                    Room room = findRoomByNumber(Integer.parseInt(data[3]));
                    LocalDate checkIn = LocalDate.parse(data[4]);
                    LocalDate checkOut = LocalDate.parse(data[5]);
                    if (room != null) {
                        Booking booking = new Booking(bookingId, customer, room, checkIn, checkOut);
                        bookings.add(booking);
                        // Update bookingIdCounter
                        int id = Integer.parseInt(bookingId.substring(1));
                        if (id >= bookingIdCounter) {
                            bookingIdCounter = id + 1;
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("Error loading bookings: " + e.getMessage());
            }
        }
    }

    private void saveRooms() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ROOMS_FILE))) {
            for (Room room : rooms) {
                writer.println(room.getRoomNumber() + "," + room.getRoomType() + "," + room.getPricePerNight());
            }
        } catch (IOException e) {
            System.err.println("Error saving rooms: " + e.getMessage());
        }
    }

    private void saveBookings() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(BOOKINGS_FILE))) {
            for (Booking booking : bookings) {
                writer.println(booking.getBookingId() + "," + booking.getCustomer().getName() + ","
                        + booking.getCustomer().getEmail() + "," + booking.getRoom().getRoomNumber() + ","
                        + booking.getCheckInDate() + "," + booking.getCheckOutDate());
            }
        } catch (IOException e) {
            System.err.println("Error saving bookings: " + e.getMessage());
        }
    }

    public List<Room> getAllRooms() {
        return rooms;
    }

    public List<Room> findAvailableRooms(LocalDate checkIn, LocalDate checkOut) {
        return rooms.stream()
                .filter(room -> isRoomAvailable(room, checkIn, checkOut))
                .collect(Collectors.toList());
    }

    public Booking makeBooking(Customer customer, int roomNumber, LocalDate checkIn, LocalDate checkOut) {
        Room roomToBook = findRoomByNumber(roomNumber);

        if (roomToBook != null && isRoomAvailable(roomToBook, checkIn, checkOut)) {
            String bookingId = "B" + bookingIdCounter++;
            Booking newBooking = new Booking(bookingId, customer, roomToBook, checkIn, checkOut);
            bookings.add(newBooking);
            saveBookings();
            return newBooking;
        }
        return null; // Booking failed
    }

    private boolean isRoomAvailable(Room room, LocalDate checkIn, LocalDate checkOut) {
        for (Booking booking : bookings) {
            if (booking.getRoom().getRoomNumber() == room.getRoomNumber()) {
                if (checkIn.isBefore(booking.getCheckOutDate()) && checkOut.isAfter(booking.getCheckInDate())) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<Booking> getAllBookings() {
        return bookings;
    }

    public boolean cancelBooking(String bookingId) {
        boolean removed = bookings.removeIf(booking -> booking.getBookingId().equalsIgnoreCase(bookingId));
        if (removed) {
            saveBookings();
        }
        return removed;
    }

    private Room findRoomByNumber(int roomNumber) {
        return rooms.stream()
                .filter(room -> room.getRoomNumber() == roomNumber)
                .findFirst()
                .orElse(null);
    }
}
