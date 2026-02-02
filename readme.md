# 📚 Library Management System (Spring Boot)

A Book Management System built using **Spring Boot**, **Spring Security (JWT)**, **JPA/Hibernate**, and **MySQL**

---

## 🚀 Features

### 1️⃣ User Management

- User registration with **email** and **unique library ID**
- Login using **email or library ID**
- Password encryption using **BCrypt**
- JWT-based authentication & authorization
- Role-based access (`ADMIN`, `USER`)

### 2️⃣ Book Management (Admin only)

- Add new books
- Update book details
- Delete books
- View all available books

### 3️⃣ Borrow & Return Books

- Users can borrow **only one book at a time**
- Tracks:
  - Borrow date
  - Due date
  - Return date

- Automatically calculates **late fees** on return

### 4️⃣ Monthly Report Scheduler

Runs **at the end of each month** and generates:

- 📕 Books borrowed in the month
- 📗 Books returned in the month
- ⏰ Overdue books with late fees
- 👤 User activity summary (borrowed vs returned count)

> For development/testing, the scheduler can be temporarily configured to run every minute.

---

## 🧱 Tech Stack

- Java 17+
- Spring Boot 3.x
- Spring Security (JWT)
- Spring Data JPA
- MySQL
- Maven
- Lombok

---

## 📂 Project Structure (High Level)

```
library-management
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
├── readme.md
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── exam
│   │   │           └── library_management
│   │   │               ├── config
│   │   │               │   ├── DotenvConfig.java
│   │   │               │   ├── JwtProperties.java
│   │   │               │   ├── LibraryProperties.java
│   │   │               │   └── SecurityConfig.java
│   │   │               ├── controller
│   │   │               │   ├── AdminBorrowController.java
│   │   │               │   ├── AuthController.java
│   │   │               │   ├── BookAdminController.java
│   │   │               │   ├── BookUserController.java
│   │   │               │   ├── BorrowController.java
│   │   │               │   └── TestController.java
│   │   │               ├── dto
│   │   │               │   ├── ApiResponse.java
│   │   │               │   ├── BorrowResponse.java
│   │   │               │   ├── LoginRequest.java
│   │   │               │   ├── LoginResponse.java
│   │   │               │   ├── MonthlyReport.java
│   │   │               │   ├── RegisterRequest.java
│   │   │               │   ├── UserActivitySummary.java
│   │   │               │   └── UserResponse.java
│   │   │               ├── entity
│   │   │               │   ├── Book.java
│   │   │               │   ├── BorrowRecord.java
│   │   │               │   └── User.java
│   │   │               ├── enums
│   │   │               │   ├── BookStatus.java
│   │   │               │   └── Role.java
│   │   │               ├── exception
│   │   │               │   ├── BadRequestException.java
│   │   │               │   ├── DuplicateResourceException.java
│   │   │               │   ├── GlobalExceptionHandler.java
│   │   │               │   └── ResourceNotFoundException.java
│   │   │               ├── LibraryManagementApplication.java
│   │   │               ├── repository
│   │   │               │   ├── BookRepository.java
│   │   │               │   ├── BorrowRecordRepository.java
│   │   │               │   └── UserRepository.java
│   │   │               ├── security
│   │   │               │   ├── CustomUserDetailsService.java
│   │   │               │   ├── JwtAuthenticationFilter.java
│   │   │               │   └── JwtUtil.java
│   │   │               └── service
│   │   │                   ├── AuthService.java
│   │   │                   ├── BookService.java
│   │   │                   ├── BorrowService.java
│   │   │                   ├── MonthlyReportService.java
│   │   │                   └── UserService.java
│   │   └── resources
│   │       ├── application.yaml
│   │       ├── static
│   │       └── templates
│   └── test
│       └── java
│           └── com
│               └── exam
│                   └── library_management
│                       └── LibraryManagementApplicationTests.java
└── target
    ├── classes
    │   ├── application.yaml
    │   └── com
    │       └── exam
    │           └── library_management
    │               ├── config
    │               │   ├── DotenvConfig.class
    │               │   ├── JwtProperties.class
    │               │   ├── LibraryProperties.class
    │               │   └── SecurityConfig.class
    │               ├── controller
    │               │   ├── AdminBorrowController.class
    │               │   ├── AuthController.class
    │               │   ├── BookAdminController.class
    │               │   ├── BookUserController.class
    │               │   ├── BorrowController.class
    │               │   └── TestController.class
    │               ├── dto
    │               │   ├── ApiResponse.class
    │               │   ├── BorrowResponse.class
    │               │   ├── LoginRequest.class
    │               │   ├── LoginResponse.class
    │               │   ├── MonthlyReport.class
    │               │   ├── RegisterRequest.class
    │               │   ├── UserActivitySummary.class
    │               │   └── UserResponse.class
    │               ├── entity
    │               │   ├── Book.class
    │               │   ├── BorrowRecord.class
    │               │   └── User.class
    │               ├── enums
    │               │   ├── BookStatus.class
    │               │   └── Role.class
    │               ├── exception
    │               │   ├── BadRequestException.class
    │               │   ├── DuplicateResourceException.class
    │               │   ├── GlobalExceptionHandler.class
    │               │   └── ResourceNotFoundException.class
    │               ├── LibraryManagementApplication.class
    │               ├── repository
    │               │   ├── BookRepository.class
    │               │   ├── BorrowRecordRepository.class
    │               │   └── UserRepository.class
    │               ├── security
    │               │   ├── CustomUserDetailsService.class
    │               │   ├── JwtAuthenticationFilter.class
    │               │   └── JwtUtil.class
    │               └── service
    │                   ├── AuthService.class
    │                   ├── BookService.class
    │                   ├── BorrowService.class
    │                   ├── MonthlyReportService.class
    │                   └── UserService.class
    ├── generated-sources
    │   └── annotations
    ├── generated-test-sources
    │   └── test-annotations
    ├── maven-status
    │   └── maven-compiler-plugin
    │       ├── compile
    │       │   └── default-compile
    │       │       ├── createdFiles.lst
    │       │       └── inputFiles.lst
    │       └── testCompile
    │           └── default-testCompile
    │               ├── createdFiles.lst
    │               └── inputFiles.lst
    └── test-classes
        └── com
            └── exam
                └── library_management
                    └── LibraryManagementApplicationTests.class

```

---

## ⚙️ Prerequisites

Make sure you have the following installed:

- **Java 17 or higher**
- **Maven 3.8+**
- **MySQL 8+**
- **Git**
- Postman / curl (for API testing)

---

## 🗄️ Database Setup

1. Start MySQL
2. Create a database:

   ```sql
   CREATE DATABASE library_db;
   ```

3. Update credentials in `application.yaml` :

   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/library_db
       username: root
       password: your_password
   ```

> Tables will be auto-created by Hibernate on application startup.

---

## ▶️ Running the Application

From the project root:

```bash
mvn clean install
mvn spring-boot:run
```

The application will start on:

```
http://localhost:8080
```

---

## 🔐 Authentication Flow

### 1. Register User

- `POST /api/auth/register`

### 2. Login

- `POST /api/auth/login`
- Returns a **JWT token**

### 3. Use JWT Token

Add this header to all protected APIs:

```
Authorization: Bearer <JWT_TOKEN>
```

---

## 📘 API Testing Guide

### 👤 User APIs

| Action   | Method | Endpoint             |
| -------- | ------ | -------------------- |
| Register | POST   | `/api/auth/register` |
| Login    | POST   | `/api/auth/login`    |

---

### 📚 Book APIs

| Action      | Method                               | Role  |
| ----------- | ------------------------------------ | ----- |
| Add Book    | POST `/api/admin/books/add`          | ADMIN |
| Update Book | PUT `/api/admin/books/{id}`          | ADMIN |
| Delete Book | DELETE `/api/admin/books/delete{id}` | ADMIN |
| View Books  | GET `api/user/books`                 | USER  |
| View Books  | GET `api/admin/books`                | ADMIN |

---

### 🔄 Borrow / Return APIs

| Action      | Method                                |
| ----------- | ------------------------------------- |
| Borrow Book | POST `/api/user/borrow/book/{bookId}` |
| Return Book | POST `/api/user/borrow/return`        |

> ⚠️ Only one active borrow allowed per user.

---

## 📊 Monthly Report Scheduler

- Default (production):

  ```java
  @Scheduled(cron = "0 59 23 L * ?") // Last day of month
  ```

- Test mode (development):

  ```java
  @Scheduled(cron = "0 */1 * * * *") // Every minute
  ```

Reports are logged to the console for review.

---

## 🧪 Testing with curl (Example)

```bash
curl -X POST http://localhost:8080/api/user/borrow/return \
  -H "Authorization: Bearer <JWT_TOKEN>" \
  -H "Content-Type: application/json"
```

---

## 🧯 Error Handling

- Centralized exception handling
- Consistent API response format:

```json
{
  "success": false,
  "message": "Error message",
  "data": null
}
```

---

## ✅ Coding Practices Followed

- MVC architecture
- Loose coupling & modular design
- DTO-based responses
- Custom exceptions
- Secure password storage
- Clean JPQL queries
- Role-based authorization

---

## 👨‍💻 Notes for Reviewers

- Scheduler logic can be extended to:
  - Export CSV / PDF
  - Store reports in DB

- JWT & security flow is production-aligned
- Designed for scalability & clarity over shortcuts

---

## 🏁 Conclusion

This project demonstrates:

- Real-world Spring Boot design
- Secure authentication
- Clean business logic separation
- Practical scheduling & reporting

Perfectly suitable for **technical evaluation and code review**.

---

Happy reviewing! 🚀
