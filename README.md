# Electricity Bill Management System

This is a desktop application developed using Java Swing and MySQL for managing electricity billing operations. The system allows an Admin to generate bills and a Customer to view their billing information.

## Project Objective

To streamline electricity bill generation and viewing using a simple and intuitive interface. This project was built as part of my academic learning to apply Java and SQL skills practically.

## Technologies Used

- Java (Swing) – User Interface
- MySQL – Database
- JDBC – Java Database Connectivity
- XAMPP (optional) – For managing MySQL server locally

## Features

### Admin
- Secure login
- Generate monthly electricity bills
- Inputs: Meter number, units consumed, and month
- Automatically calculates total bill based on a fixed formula
- Saves the generated bill into the database

### Customer
- Login with meter number
- View past bill details from the database
- Shows month, units, amount, and payment status

## Bill Calculation Formula

Total Bill = (Units × 9) + Meter Rent + Service Charge + Service Tax + Swachh Bharat Cess + Fixed Tax  
(All charges are currently hardcoded in the Java application.)

## Database Overview

**Database Name:** `project2`

**Tables:**
- `login` – Admin and Customer login credentials
- `customer` – Customer profile data
- `bill` – Stores generated bill records
- `meter_info` – Additional meter-related information
- `tax` – Billing components like cost per unit, taxes, and service charges

## How to Run the Project

1. Install XAMPP and start the MySQL server.
2. Import `project2.sql` into phpMyAdmin or via command-line.
3. Make sure the `mysql-connector-java.jar` file is placed in a `lib` directory.
4. Open a terminal in the `src` folder and compile the Java files:

```bash
javac -cp ".;../lib/mysql-connector-java-<version>.jar" *.java
