
# 🔐 Spring Security - Database Authentication & Authorization

Hey there! 👋  

This is a **Spring Boot** project showing how to secure your app with **Spring Security** using a database for authentication and authorization. Users and roles are stored in a database (MySQL/H2), and passwords are safely hashed with **BCrypt**. Only the right users can access the right endpoints—simple and secure!  

---

## 🚀 What’s Inside

- Login and user authentication from a database  
- Role-based access control (`USER`, `ADMIN`)  
- Passwords are encrypted with **BCrypt**  
- Secure REST endpoints for different roles  
- Optional custom login and access-denied pages  

---

## 🧰 Tech Stack

- Spring Boot 3  
- Spring Security  
- Spring Data JPA   
- MySQL
- Maven  

---

## 📁 How the Project Looks

```
src/
 ├── main/
 │   ├── java/com/example/security/
 │   │   ├── config/       # Security configuration
 │   │   ├── controller/   # REST controllers
 │   │   ├── entity/       # User & Role entities
 │   │   ├── repository/   # JPA repositories
 │   │   ├── service/      # Custom UserDetailsService
 │   │   └── SecurityApplication.java
 │   └── resources/
 │       ├── application.properties
 │       └── data.sql / schema.sql
```

---

## 🧪 Endpoints to Try

| Endpoint      | Who Can Access? | Notes                        |
|---------------|----------------|-------------------------------|
| `/hello`      | Anyone         | Just a friendly hello!       |
| `/user`       | USER role      | Only logged-in users         |
| `/admin`      | ADMIN role     | Admins only, keep it secret 😉 |

---

## 🔧 Getting Started

1. Clone the repo:  
```bash
git clone <your-repo-url>
```

2. Set up your database in `application.properties`:  
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/db_name
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

3. Run the app:  
```bash
mvn spring-boot:run
```

4. Open Postman or your browser and test the endpoints!

---

