# Train Ticket Management System

A simple console-based Train Ticket Management System developed using **Core Java, JDBC, and MySQL**.

## Features
- View all available trains
- Search trains by source and destination
- Book train tickets
- View passenger bookings
- Cancel bookings
- Automatically update available seats

## Technologies Used
- Java
- JDBC
- MySQL
- VS Code

## Database
Database name: `train_management`

Tables:
- `trains`
- `bookings`

## How to Run
1. Install Java and MySQL.
2. Create the `train_management` database and required tables.
3. Add your MySQL username and password in `DBConnection.java`.
4. Ensure the MySQL Connector JAR is inside the `lib` folder.
5. Compile and run the project using the following command in the project folder:

```bash
javac -cp ".;lib/mysql-connector-j-26.10.0.jar" *.java
java -cp ".;lib/mysql-connector-j-26.10.0.jar" Main
```

## Developed By
Student Project – Train Ticket Management System
