# 🏨 Hotel Room Booking System

A simple, console-based hotel room booking system written in Java.

## 📜 Description

This project is a command-line application that allows users to manage a hotel's room bookings. Users can view available rooms, make new bookings, view all existing bookings, and cancel a booking. The application data is persisted to CSV files, so the state is saved between sessions.

## ✨ Features

- **View All Rooms:** See a list of all rooms in the hotel, including their type, price, and availability.
- **Search for Available Rooms:** Find rooms that are available for a given date range.
- **Make a Booking:** Book a room for a customer for a specific period.
- **View All Bookings:** See a list of all current bookings.
- **Cancel a Booking:** Cancel an existing booking by its ID.
- **Data Persistence:** All room and booking data is saved to and loaded from CSV files in the `data` directory.

## 🚀 Getting Started

### Prerequisites

- **Java Development Kit (JDK):** You need to have a JDK (version 8 or higher) installed on your system and configured in your system's PATH.

### Compilation

1. Open a terminal or command prompt.
2. Navigate to the root directory of the project:
   ```sh
   cd c:\Users\rajpr\OneDrive\Desktop\Hotel Room Booking
   ```
3. Compile the Java source files:
   ```sh
   javac -d bin src\com\hotel\*.java src\com\hotel\model\*.java src\com\hotel\service\*.java
   ```

### Running the Application

After successful compilation, run the application with the following command:

```sh
java -cp bin com.hotel.Main
```

You will see the main menu of the hotel booking system in your terminal.

## 📂 Code Structure

```
C:.
+---bin
|   +---com
|       +---hotel
|           |   Main.class
|           |
|           +---model
|           |       Booking.class
|           |       Customer.class
|           |       Room.class
|           |
|           +---service
|                   HotelService.class
|
+---data
+---src
    +---com
        +---hotel
            |   Main.java
            |
            +---model
            |       Booking.java
            |       Customer.java
            |       Room.java
            |
            +---service
                    HotelService.java
```

## 📄 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

