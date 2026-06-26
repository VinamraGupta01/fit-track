<h1 align="center">
🏋️ FitTrack
</h1>

<h3 align="center">
A Production-Style Full Stack Fitness Tracking & Recovery Platform
</h3>

<p align="center">

Built with <strong>Spring Boot 3</strong>, <strong>React</strong>, <strong>MySQL</strong>, <strong>JWT Authentication</strong>, and <strong>Docker</strong>

Designed to demonstrate real-world backend architecture, secure authentication, REST API design, and modern full-stack development practices.

</p>

---

# 📖 Overview

FitTrack is a professional SaaS-inspired fitness platform that allows users to manage workouts, monitor recovery, calculate readiness scores, and visualize fitness progress through an interactive dashboard.

Unlike a basic CRUD project, FitTrack focuses on **clean architecture**, **secure authentication**, **production-ready backend practices**, and **scalable system design**.

The project was built to simulate how modern fitness applications organize backend services, business logic, database relationships, and frontend interactions.

---

# ✨ Features

## 🔐 Authentication & Security

- JWT Authentication
- BCrypt Password Encryption
- Spring Security
- Stateless Authentication
- Protected REST APIs
- Role-Based Authorization (User/Admin)

---

## 👤 User Management

- User Registration
- Secure Login
- User Profile
- Password Update
- Personal Fitness Goals

---

## 🏋️ Workout Management

- Create Workouts
- Update Workouts
- Delete Workouts
- Workout Categories
- Workout History
- Calories Tracking
- Workout Duration
- Intensity Levels

---

## ❤️ Recovery Tracking

Track important recovery metrics including:

- Sleep Hours
- Sleep Quality
- Stress Level
- Muscle Soreness

---

## ⚡ Readiness Engine

A custom recovery algorithm calculates a daily readiness score based on:

- Sleep
- Stress
- Recovery
- Training Load

The readiness score provides recommendations such as:

- Recovery Day
- Moderate Workout
- High Intensity Training

---

## 📊 Dashboard

Interactive dashboard with:

- Total Workouts
- Weekly Activity
- Recovery Trends
- Readiness Score
- Calories Burned
- Workout Analytics

---

## 👨‍💼 Admin Features

- Manage Users
- Workout Categories
- Platform Statistics
- User Monitoring

---

# 🏗️ Architecture

The backend follows a clean layered architecture.

```
Client (React)

        │

REST API

        │

Controllers

        │

Services

        │

Repositories

        │

MySQL Database
```

Project follows separation of concerns:

- Controller Layer
- Service Layer
- Repository Layer
- DTO Layer
- Security Layer
- Exception Handling Layer

---

# 🛠️ Tech Stack

## Backend

- Java 17
- Spring Boot 3
- Spring Security
- Spring Data JPA
- Hibernate
- JWT Authentication
- Maven

---

## Frontend

- React
- React Router
- Axios
- Tailwind CSS
- Vite
- Recharts

---

## Database

- MySQL

---

## DevOps

- Docker
- Docker Compose
- Render Deployment

---

## Documentation

- Swagger / OpenAPI

---

# 📂 Project Structure

```
fit-track/

│

├── backend/

│   ├── config

│   ├── controller

│   ├── dto

│   ├── entity

│   ├── enums

│   ├── exception

│   ├── repository

│   ├── security

│   ├── service

│   └── resources

│

├── frontend/

│   ├── api

│   ├── components

│   ├── context

│   ├── pages

│   └── assets

│

├── docker-compose.yml

├── README.md

└── .env.example
```

---

# 🗄️ Database Design

The application uses relational database modeling.

### Main Tables

- Users
- Workout Categories
- Workouts
- Workout Sessions
- Recovery Logs
- Readiness Scores

Relationships are managed using Hibernate/JPA with proper foreign key mappings.

---

# 🔒 Security

Implemented using Spring Security.

✔ JWT Authentication

✔ BCrypt Password Encoding

✔ Stateless Authentication

✔ Protected REST Endpoints

✔ Role-Based Authorization

---

# 🚀 REST API

## Authentication

```
POST /api/auth/register

POST /api/auth/login
```

---

## Users

```
GET /api/users/me

PUT /api/users/me

PUT /api/users/me/password
```

---

## Workouts

```
POST /api/workouts

GET /api/workouts

PUT /api/workouts/{id}

DELETE /api/workouts/{id}
```

---

## Recovery

```
POST /api/recovery

GET /api/recovery
```

---

## Readiness

```
GET /api/readiness/today
```

---

## Dashboard

```
GET /api/dashboard/summary

GET /api/dashboard/trends
```

---

## Admin

```
GET /api/admin/users

GET /api/admin/statistics

POST /api/admin/categories

PUT /api/admin/categories/{id}

DELETE /api/admin/categories/{id}
```

---

# ⚙️ Running Locally

## 1 Clone Repository

```bash
git clone https://github.com/yourusername/fit-track.git
```

---

## 2 Backend

```bash
cd backend

mvn spring-boot:run
```

---

## 3 Frontend

```bash
cd frontend

npm install

npm run dev
```

---

## 4 Using Docker

```bash
docker compose up --build
```

---

# 🌐 Default URLs

| Service | URL |
|----------|-----|
| React | http://localhost:5173 |
| Spring Boot | http://localhost:8080 |
| Swagger UI | http://localhost:8080/swagger-ui/index.html |

---

# 📚 Key Backend Concepts Demonstrated

- Layered Architecture

- RESTful API Design

- DTO Pattern

- Repository Pattern

- Dependency Injection

- JWT Authentication

- Spring Security

- Exception Handling

- Validation

- Pagination

- Sorting

- Entity Relationships

- Docker Deployment

- Clean Code Principles

---

# 💡 Why This Project?

FitTrack was built to simulate how production backend systems are structured rather than focusing only on CRUD operations.

It demonstrates:

- Scalable architecture
- Secure authentication
- Maintainable code organization
- Database modeling
- REST API best practices
- Full-stack integration
- Dockerized deployment

---

# 🎯 Future Improvements

- Email Verification

- Password Reset

- OAuth Login

- Notifications

- Exercise Library

- AI Workout Recommendations

- Nutrition Tracking

- Wearable Device Integration

- Cloud Storage

- Kubernetes Deployment

---

# 👨‍💻 Author

### Vinamra Gupta

Backend Developer

📧 Email

**vinamra.gupta.dev@gmail.com**

💼 LinkedIn

**https://www.linkedin.com/in/vinamra-gupta-0aa4b4375**

GitHub

**https://github.com/VinamraGupta01**

---

# ⭐ Support

If you found this project useful, consider giving it a **⭐ Star** on GitHub.

It motivates continued development and helps others discover the project.

---

<h3 align="center">
Built with ❤️ using Spring Boot, React, MySQL & Docker
</h3>
