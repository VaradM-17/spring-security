# 🔐 Spring Security - Database Authentication & Authorization

Hey there! 👋

This is a **Spring Boot** project showing how to secure your app with **Spring Boot + Spring Security + JWT** project!  
This app shows how to secure your REST APIs using tokens instead of sessions.  
Users and roles live in a **MySQL database**, passwords are **encrypted with BCrypt**, and access is managed through **JWT tokens** — simple, modern, and secure! 🚀

---

## 🚀 What’s Inside

- JWT (token-based) authentication
- Role-based authorization (`USER`, `ADMIN`)
- Encrypted passwords using BCrypt
- Secure REST APIs for multiple roles
- Stateless security — no sessions required!

---

## 🧰 Tech Stack

- Spring Boot 3
- Spring Security
- JSON Web Token (JWT)
- Spring Data JPA
- MySQL
- Maven

---

## 📁 Project Structure

```
src/
 ├── main/
 │   ├── java/com/example/security/
 │   │   ├── config/         # Security & JWT configuration
 │   │   ├── controller/     # Auth & test controllers
 │   │   ├── dto/            # Request/response DTOs
 │   │   ├── entity/         # User & Role entities
 │   │   ├── repository/     # JPA repositories
 │   │   ├── service/        # UserDetailsService & JWT logic
 │   │   └── SecurityApplication.java
 │   └── resources/
 │       ├── application.properties

```

---

## 🔑 Authentication Flow

1. User signs up → info stored in DB (with encrypted password).
2. User logs in → receives a **JWT token**.
3. Token is sent in the `Authorization` header for all protected routes.
4. **JWT Filter** validates token before granting access.

---

## 🧪 Endpoints to Try

| Endpoint           | Method | Who Can Access? | Description         |
| ------------------ | ------ | --------------- | ------------------- |
| `/api/auth/signup` | POST   | Anyone          | Register a new user |
| `/api/auth/login`  | POST   | Anyone          | Get JWT token       |
| `/api/user`        | GET    | USER or ADMIN   | Requires JWT        |
| `/api/admin`       | GET    | ADMIN only      | Admin route         |
| `/api/hello`       | GET    | Public          | No auth required    |

---

## 🔧 Getting Started

1. Clone the repo

   ```bash
   git clone <your-repo-url>
   ```

2. Update your database settings in `application.properties`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/db_name
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update
   ```

3. Run the app

   ```bash
   mvn spring-boot:run
   ```

4. Test in Postman using your JWT token!

---
