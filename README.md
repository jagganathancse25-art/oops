# OOPS Lab Programs

This repository contains Object-Oriented Programming (OOPS) lab experiments in Java.

## Experiments

1. **Ex1_ElectricityBill.java** - Electricity Bill Calculator (Domestic/Commercial)
2. **Ex2/** - Package-based Converters (Currency, Distance, Time)
   - `Ex2/currency/CurrencyConverter.java`
   - `Ex2/distance/DistanceConverter.java`
   - `Ex2/time/TimeConverter.java`
   - `Ex2/Main.java`
3. **Ex3_VehicleInheritance.java** - Inheritance Program for Vehicle Class (Car, Bike, Truck)
4. **Ex4_AbstractLibraryMember.java** - Abstract Class for Library Members
5. **Ex5_CircularQueue.java** - ADT Circular Queue with Exception Handling
6. **Ex6_WrapperImmutableDemo.java** - Immutable Nature of Wrapper Classes
7. **Ex7_MultiThreadSort.java** - Multi-Thread Program for Array Sorting
8. **Ex8_RailwayBooking.java** - Inter-Thread Communication (Ticket Booking)
9. **Ex9_StringMenu.java** - String Operations using ArrayList
10. **Ex10_ListFiles.java** - File Handling (List files in directory)
11. **Ex11_StudentManagementApp.java** - CRUD Application using JavaFX and JDBC
12. **Ex12_PersonalExpenseTracker.java** - Personal Expense Tracker (Java + MySQL + JDBC)

## Notes
- **Ex2**: Use the folder structure under `Ex2/`. Compile and run from the `Ex2` directory:
  ```
  javac currency/*.java distance/*.java time/*.java Main.java
  java Main
  ```
- **Ex11** requires MySQL database setup, JDBC driver, and JavaFX modules.
- **Ex12** requires MySQL database `expense_tracker` with table:
  ```sql
  CREATE DATABASE expense_tracker;
  USE expense_tracker;
  CREATE TABLE expenses (
      id INT AUTO_INCREMENT PRIMARY KEY,
      title VARCHAR(100),
      category VARCHAR(50),
      amount DOUBLE,
      expense_date DATE
  );
  ```
