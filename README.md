# Fit Track — Fitness Recovery & Workout Tracking Platform

Fit Track is a professional full-stack SaaS-style fitness platform built with **Spring Boot 3, React, and MySQL**. It demonstrates internship-ready backend engineering: layered architecture, JWT security, DTOs, validation, exception handling, pagination, sorting, role-based authorization, Docker, and API documentation.

> Branding note: no image logo was attached in this chat, so the app includes a premium inline SVG/wordmark logo. Replace `frontend/src/components/Logo.jsx` if you want to use your own logo asset.

## 1. Complete Folder Structure

```text
fit-track/
├── backend/
│   ├── Dockerfile
│   ├── pom.xml
│   └── src/main/
│       ├── java/com/fittrack/
│       │   ├── FitTrackApplication.java
│       │   ├── config/             # CORS, OpenAPI, data seeding
│       │   ├── controller/         # Thin REST controllers
│       │   ├── dto/                # Request/response DTOs
│       │   ├── entity/             # JPA entities
│       │   ├── enums/              # Role/intensity enums
│       │   ├── exception/          # Global exception handling
│       │   ├── repository/         # Spring Data JPA repositories
│       │   ├── security/           # JWT + Spring Security
│       │   └── service/            # Business logic
│       └── resources/
│           └── application.yml
├── frontend/
│   ├── Dockerfile
│   ├── index.html
│   ├── package.json
│   ├── vite.config.js
│   ├── tailwind.config.js
│   └── src/
│       ├── api/axios.js
│       ├── components/             # Layout, logo, route guards, UI cards
│       ├── context/AuthContext.jsx
│       ├── pages/                  # Landing, auth, dashboards, profile
│       └── main.jsx
├── docker-compose.yml
├── .env.example
└── RENDER_DEPLOYMENT.md
```

## 2. Database Schema

### `users`
| Column | Type | Notes |
|---|---|---|
| id | BIGINT PK | Auto increment |
| name | VARCHAR | Required |
| email | VARCHAR UNIQUE | Login username |
| password | VARCHAR | BCrypt hash |
| role | ENUM(USER, ADMIN) | Authorization |
| height_cm, weight_kg | DOUBLE | Profile metrics |
| goal | VARCHAR | User goal |
| created_at, updated_at | DATETIME | Auditing |

### `workout_categories`
| Column | Type | Notes |
|---|---|---|
| id | BIGINT PK | Auto increment |
| name | VARCHAR UNIQUE | Strength, Cardio, Mobility |
| description | VARCHAR | Optional |
| active | BOOLEAN | Admin controlled |
| created_at, updated_at | DATETIME | Auditing |

### `workouts`
| Column | Type | Notes |
|---|---|---|
| id | BIGINT PK | Auto increment |
| user_id | FK users.id | Owner |
| category_id | FK workout_categories.id | Optional category |
| title | VARCHAR | Workout name |
| intensity | ENUM(LOW, MODERATE, HIGH) | Training load |
| duration_minutes | INT | Planned/actual duration |
| calories_burned | INT | Estimated burn |
| workout_date | DATE | History date |
| notes | TEXT | Optional |
| created_at, updated_at | DATETIME | Auditing |

### `workout_sessions`
| Column | Type | Notes |
|---|---|---|
| id | BIGINT PK | Auto increment |
| user_id | FK users.id | Owner |
| workout_id | FK workouts.id | Source workout |
| started_at, ended_at | DATETIME | Session window |
| perceived_exertion | INT | 1-10 RPE |
| notes | TEXT | Optional |
| created_at, updated_at | DATETIME | Auditing |

### `recovery_logs`
| Column | Type | Notes |
|---|---|---|
| id | BIGINT PK | Auto increment |
| user_id | FK users.id | Owner |
| log_date | DATE | One per user/date |
| sleep_hours | DOUBLE | 0-14 |
| sleep_quality | INT | 1-10 |
| stress_level | INT | 1-10 |
| muscle_soreness | INT | 1-10 |
| recovery_score | INT | 0-100 generated |
| created_at, updated_at | DATETIME | Auditing |

### `readiness_scores`
| Column | Type | Notes |
|---|---|---|
| id | BIGINT PK | Auto increment |
| user_id | FK users.id | Owner |
| score_date | DATE | One per user/date |
| score | INT | 0-100 generated |
| recommendation | VARCHAR | Training guidance |
| created_at, updated_at | DATETIME | Auditing |

## 3. Architecture Decisions

- **Layered architecture:** Controller → Service → Repository. Controllers only validate inputs and delegate.
- **DTO pattern:** Entities are not exposed directly; request/response contracts stay stable.
- **JWT security:** Stateless REST API, BCrypt passwords, role-based route protection.
- **JPA relationships:** Proper `@ManyToOne` mappings from domain records to the owning user.
- **Exception handling:** `@RestControllerAdvice` returns consistent `ApiResponse` errors.
- **Pagination/sorting/search:** Workout and admin user lists use `Pageable` and query methods.
- **Readiness engine:** Simple, explainable formula using sleep, stress, soreness, and recent training load — realistic for a student project and easy to discuss in interviews.
- **Frontend:** Premium SaaS UI using Tailwind, React Router, Axios interceptors, responsive dark mode layout, Recharts analytics.

## 4. Quick Start

### Prerequisites
- Java 17+
- Maven 3.9+
- Node.js 18+
- MySQL 8+
- Docker Desktop optional

### Run with Docker Compose
```bash
cp .env.example .env
docker compose up --build
```

- Frontend: http://localhost:5173
- Backend API: http://localhost:8080
- Swagger UI: http://localhost:8080/swagger-ui/index.html

### Run Locally Without Docker

1. Create MySQL database:
```sql
CREATE DATABASE fittrack_db;
```

2. Backend:
```bash
cd backend
# Set DB env vars if needed, or edit src/main/resources/application.yml
mvn spring-boot:run
```

3. Frontend:
```bash
cd frontend
npm install
npm run dev
```

## Demo Accounts
Seeded automatically on backend startup:

| Role | Email | Password |
|---|---|---|
| Admin | admin@fittrack.dev | Admin@123 |
| User | user@fittrack.dev | User@123 |

## API Response Format
```json
{
  "success": true,
  "message": "Operation successful",
  "data": {}
}
```

## Main API Endpoints

### Auth
- `POST /api/auth/register`
- `POST /api/auth/login`

### User
- `GET /api/users/me`
- `PUT /api/users/me`
- `PUT /api/users/me/password`

### Workouts
- `POST /api/workouts`
- `GET /api/workouts?page=0&size=10&sort=workoutDate,desc&search=push`
- `PUT /api/workouts/{id}`
- `DELETE /api/workouts/{id}`

### Recovery & Readiness
- `POST /api/recovery`
- `GET /api/recovery`
- `GET /api/readiness/today`

### Dashboard
- `GET /api/dashboard/summary`
- `GET /api/dashboard/trends`

### Admin
- `GET /api/admin/users?search=demo&page=0&size=10`
- `DELETE /api/admin/users/{id}`
- `GET /api/admin/statistics`
- `POST /api/admin/categories`
- `PUT /api/admin/categories/{id}`
- `DELETE /api/admin/categories/{id}`

## Interview Talking Points

- Why JWT stateless auth fits REST APIs.
- Why services own business logic and controllers stay thin.
- How DTOs prevent overexposing database structure.
- How readiness score is calculated and can evolve into ML later.
- How pagination and search keep APIs scalable.
- How Docker Compose makes the project easy for reviewers to run.
