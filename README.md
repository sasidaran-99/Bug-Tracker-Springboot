# 🐞 Bug Tracker Backend

A structured backend application built to manage bugs/issues in a system.  
This project demonstrates real-world backend development concepts including REST APIs, database integration, CRUD operations, and pagination.

---

## 🚀 Features

- Create new bugs
- View all bugs
- Update existing bugs
- Delete bugs
- Pagination support
- PostgreSQL database integration
- Tested using Postman
- Clean layered backend architecture

---

## 🏗️ Project Architecture

This project follows a layered architecture:

```
Controller → Service → Repository → Database
```

### Structure
```
src/main/java/com/bugtracker
│
├── controller
├── service
├── repository
├── model
└── BugTrackerApplication
```

This ensures:
- Clean code structure  
- Separation of concerns  
- Easy scalability  

---

## 🗄️ Database Used

**PostgreSQL**

### Table: bug

| Column       | Type     |
|--------------|----------|
| id           | bigint   |
| title        | varchar  |
| description  | varchar  |
| priority     | varchar  |
| status       | varchar  |

---

## 📡 API Endpoints

### ➤ Create Bug
```
POST /bugs
```

### ➤ Get All Bugs
```
GET /bugs
```

### ➤ Update Bug
```
PUT /bugs/{id}
```

### ➤ Delete Bug
```
DELETE /bugs/{id}
```

### ➤ Pagination
```
GET /bugs/page?page=0&size=5
```

---

## 🧪 Sample JSON Request

```json
{
  "title": "Login page bug",
  "description": "Login button not working",
  "priority": "HIGH",
  "status": "OPEN"
}
```

---

## ⚙️ How to Run This Project

1. Clone the repository
```
git clone https://github.com/sasidaran-99/bug-tracker-springboot.git
```

2. Open in IntelliJ / any IDE

3. Configure PostgreSQL in:
```
application.properties
```

4. Run Spring Boot application

5. Test APIs using Postman

---

## 📈 What I Learned From This Project

- Building REST APIs using Spring Boot
- Layered backend architecture
- PostgreSQL integration
- CRUD operations
- Pagination implementation
- Using Git & GitHub for version control

---

## 🔮 Future Improvements

- Validation
- Exception handling
- Swagger documentation
- Authentication & Security
- Deployment

---

## 👨‍💻 Author
**Sasidaran S**

---

⭐ If you like this project, feel free to star the repository!
