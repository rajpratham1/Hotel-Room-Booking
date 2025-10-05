# 🏨 Hotel Room Booking System

![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg?style=for-the-badge)
![Language: Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)

A simple yet robust console-based hotel room booking system developed in Java. This application provides a complete, menu-driven command-line interface (CLI) for managing hotel reservations, making it a perfect example of core Java principles and object-oriented design in action.

The system handles all essential operations, including viewing rooms, checking availability by date, making and canceling bookings, and persisting all data to CSV files to ensure no information is lost between sessions.

## ✨ Key Features

-   **📜 Menu-Driven Interface:** Simple and intuitive CLI for easy navigation.
-   **🏡 View All Rooms:** Get a complete list of all rooms with their type, price, and number.
-   **📅 Date-Based Search:** Find available rooms for a specific check-in and check-out period.
-   **✍️ Make a Booking:** Create new bookings for customers with just a few keystrokes.
-   **📋 View All Bookings:** Display a comprehensive list of all current reservations.
-   **❌ Cancel a Booking:** Easily cancel an existing booking using its unique ID.
-   **💾 CSV Data Persistence:** All room and booking data is automatically saved to and loaded from `.csv` files, acting as a lightweight database.

## 🛠️ Technologies Used

-   **Core Language:** **Java**
-   **Standard Libraries:** `java.time` for modern date handling, `java.util.Scanner` for user input, and `java.io` for file persistence.

## 📂 Project Structure

The project is organized into a clean and standard Java layout to separate concerns.

```
.
├── src/
│   └── com/
│       └── hotel/
│           ├── Main.java           # UI/Presentation Layer
│           ├── model/              # Data Models (Room, Customer, Booking)
│           │   ├── Booking.java
│           │   ├── Customer.java
│           │   └── Room.java
│           └── service/            # Business Logic Layer
│               └── HotelService.java
├── bin/                            # Compiled .class files
├── data/                           # Stores rooms.csv and bookings.csv
├── LICENSE
└── README.md
```

## 🚀 Getting Started

To get a local copy up and running, follow these simple steps.

### Prerequisites

You must have the **Java Development Kit (JDK)** installed on your system and configured in your system's `PATH` environment variable.

1.  **Install JDK:** Download and install a JDK (version 8 or higher) from a vendor like [Adoptium](https://adoptium.net/temurin/releases/).
2.  **Verify Installation:** Open a **new** terminal and run `java -version` and `javac -version`. You should see the version information for both commands. If not, you may need to set up your `PATH` variable manually.

### Compilation & Execution

1.  **Navigate to the project directory:**
    ```sh
    # On Windows PowerShell, remember to use quotes if the path has spaces
    cd "c:\Users\rajpr\OneDrive\Desktop\Hotel Room Booking"
    ```

2.  **Compile the Java source files:** This command compiles all `.java` files and places the output `.class` files into the `bin` directory.
    ```sh
    javac -d bin src\com\hotel\*.java src\com\hotel\model\*.java src\com\hotel\service\*.java
    ```

3.  **Run the application:** This command executes the program from the `bin` directory.
    ```sh
    java -cp bin com.hotel.Main
    ```

## 💻 System Demonstration

<details>
<summary>Click to see a full console demonstration</summary>

```
PS C:\Users\rajpr\OneDrive\Desktop\Hotel Room Booking> java -cp bin com.hotel.Main

--- Hotel Booking Menu ---
1. View All Rooms
2. Search for Available Rooms
3. Make a Booking
4. View All Bookings
5. Cancel a Booking
0. Exit
Enter your choice: 1

--- All Rooms ---
Room{roomNumber=101, roomType='Single', pricePerNight=100.0}
Room{roomNumber=102, roomType='Single', pricePerNight=100.0}
Room{roomNumber=201, roomType='Double', pricePerNight=150.0}
Room{roomNumber=202, roomType='Double', pricePerNight=150.0}
Room{roomNumber=301, roomType='Suite', pricePerNight=250.0}

--- Hotel Booking Menu ---
1. View All Rooms
2. Search for Available Rooms
3. Make a Booking
4. View All Bookings
5. Cancel a Booking
0. Exit
Enter your choice: 3
Enter your name: John Doe
Enter your email: john.doe@example.com
Enter room number to book: 201
Enter check-in date (YYYY-MM-DD): 2025-11-15
Enter check-out date (YYYY-MM-DD): 2025-11-20

Booking successful!
Booking{bookingId='B1', customer=Customer{name='John Doe', email='john.doe@example.com'}, room=Room{roomNumber=201, roomType='Double', pricePerNight=150.0}, checkInDate=2025-11-15, checkOutDate=2025-11-20}

--- Hotel Booking Menu ---
1. View All Rooms
2. Search for Available Rooms
3. Make a Booking
4. View All Bookings
5. Cancel a Booking
0. Exit
Enter your choice: 0
Thank you for using the Hotel Booking System. Goodbye!
```
</details>

## 📄 License

This project is distributed under the MIT License. See the `LICENSE` file for more information.
