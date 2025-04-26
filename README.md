# Hotel Reservation System

Welcome to the **Hotel Reservation System** — a secure, JavaFX-based desktop application developed by **Almabrouk Ben-Omran** and **Abdulrahman Alhail** for the Secure Software Development course (DACS 3203-5). Designed with strong security principles and user-centric features, this application enables hotel receptionists and managers to efficiently manage room bookings, customer check-ins/check-outs, billing, feedback, and maintenance reports.

---

## 📋 Project Overview

The **Hotel Reservation System** allows authorized hotel staff (Admin, Manager, Receptionist) to:

- Register users (Receptionist/Manager) and set up their profiles.
- Authenticate securely with role-based dashboard access.
- Manage room availability and customer reservations.
- Handle billing operations with secure deposit and final payments.
- Submit and review customer feedback and maintenance reports.
- Perform secure check-ins and check-outs.
- Manage customer profiles, with sensitive data (e.g., QID) encrypted.
- Log critical system actions for auditing and monitoring.

The application follows the complete Secure Software Development Lifecycle (SSDLC) — from requirements gathering and threat modeling to secure coding, SAST/DAST testing, and deployment.

---

## 🚀 Key Features

- **Secure User Authentication:**  
  Passwords are salted, hashed, and stored securely.
- **Role-Based Access Control (RBAC):**  
  Different system views for Admin, Manager, and Receptionist.
- **Room Reservation & Availability Management:**  
  Seamless reservation creation, modification, and cancellation.
- **Customer Profile Management:**  
  SPII (Sensitive Personally Identifiable Information) like QID is AES-encrypted.
- **Billing & Receipts:**  
  Automatic billing with initial deposit enforcement.
- **Operational Reports:**  
  Managers can review maintenance and customer feedback reports.
- **System Logging:**  
  All critical actions (e.g., cancellations, service requests) are logged securely.
- **Secure Development Practices:**  
  - Threat modeling (STRIDE/DREAD)
  - SAST via Visual Code Grepper (VCG)
  - DAST by simulating SQLi, command injection, brute-force attacks, and integer overflows.

---

## 🛠 Technologies Used

- **Programming Language:** Java
- **GUI Framework:** JavaFX
- **Database:** MySQL (managed via XAMPP's phpMyAdmin)
- **Security Libraries:** Java Cryptography Architecture (for password hashing and QID encryption)

---

## 🧩 Project Structure

| Module | Description |
|:-------|:------------|
| User Management | Admin registers new users and manages authentication |
| Reservation System | Receptionists manage room bookings and cancellations |
| Billing System | Receptionists bill customers and issue receipts |
| Customer Profiles | Receptionists securely manage customer data |
| Reporting | Managers view operational and customer reports |
| System Logs | Tracks critical activities for security monitoring |

---

## 📚 Project Deliverables

The full project documentation is available in the repository:
- 📄 **Group Project #5 - Hotel Reservation System - 60102244 - 60104920.pdf**  
  Contains complete functional requirements, abuse/security use cases, UML diagrams, threat models, test cases, and more.
- 🗂️ **hotel_database_schema/hotel.sql**  
  Contains the full database schema and dummy data to recreate the application environment.

---

## 🖥️ Installation and Setup

Follow these steps to set up the application environment:

### 1. Install XAMPP
- Download and install [XAMPP](https://www.apachefriends.org/index.html).
- Launch **XAMPP Control Panel**.

### 2. Start Apache and MySQL
- Start both **Apache** and **MySQL** services from the XAMPP Control Panel.

### 3. Set Up the Database
- Open **phpMyAdmin** via `http://localhost/phpmyadmin/`.
- Create a new database named `hotel`.
- Import the provided **hotel.sql** file:
  - Go to the **Import** tab.
  - Upload and execute the `hotel_database_schema/hotel.sql` file.

✅ Your database, tables, and dummy data will now be ready!

### 4. Install and Configure Java MySQL Connector
To connect the Java application to the SQL server, follow these steps:

1. **Download the JDBC library**:  
   - Visit [MySQL Connector/J Downloads](https://dev.mysql.com/downloads/connector/j/).
   - Choose **Platform Independent** and download the ZIP file.
   - Unzip it. Inside the unzipped folder, locate the `mysql-connector-j-8.3.0.jar` file.

2. **Add JDBC library to your project**:  
   - In your IDE, go to **Project Structure**.
   - Add a **Library** that references the `mysql-connector-j-8.3.0.jar` file.
   - This enables you to use the JDBC API to connect and query the hotel database.

### 5. Run the Application
- Ensure you have **Java JDK** and **JavaFX SDK** installed and configured.
- Open the project in your IDE (e.g., IntelliJ IDEA, Eclipse).
- Configure your IDE to support JavaFX.
- Verify or update the database connection settings (usually `localhost`, `root` user, no password by default for XAMPP).
- Build and run the application!

---

## 👨‍💻 Contributions

| Contributor | Contributions |
|:-----------|:--------------|
| **Almabrouk Ben-Omran (60104920) [https://github.com/AlmabroukBen-Omran]** | Receptionist Dashboard, Customer Profile Management, Reservation Display/Deletion, JavaFX GUI Integration, Database Connection, Validation, SPII Encryption |
| **Abdulrahman Alhail (60102244) [https://github.com/student2244]** | Login System, Admin/Manager Dashboards, Security Features (Rate Limiting, Logging, Role-Based Access), Maintenance & Feedback Reports, Admin Log Access |

Collaborative efforts were made across the project, including design diagrams, threat modeling, and security testing phases.

---

## 🔒 Security Highlights

- ✅ Password hashing with random salt (SHA-256)
- ✅ AES-128 encryption for sensitive customer data (e.g., QID)
- ✅ Input validation on all forms
- ✅ Rate limiting for login attempts
- ✅ Role-based access control (RBAC)
- ✅ Comprehensive system logging
- ✅ SAST/DAST testing incorporated during development

---

## 📜 License

This project was developed as part of the **Secure Software Development (DACS 3203-5)** course at Qatar University and is for educational purposes only.

---

## 🤝 Acknowledgements

- Special thanks to **Dr. Hussam Fetyan [https://github.com/hussam-UDST]** for his guidance and incredible support throughout the course.
- Developed with dedication by **Almabrouk Ben-Omran** and **Abdulrahman Alhail**.

---
