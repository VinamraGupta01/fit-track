# Render Deployment Guide

## Option A: Backend on Render + MySQL on PlanetScale/Railway/Aiven

1. Push this repository to GitHub.
2. Create a managed MySQL database.
3. On Render, create a **Web Service** from `backend/`.
4. Build command:
   ```bash
   mvn clean package -DskipTests
   ```
5. Start command:
   ```bash
   java -jar target/fit-track-backend-0.0.1-SNAPSHOT.jar
   ```
6. Add environment variables:
   - `DB_HOST`
   - `DB_PORT`
   - `DB_NAME`
   - `DB_USERNAME`
   - `DB_PASSWORD`
   - `JWT_SECRET`
   - `JWT_EXPIRATION_MS=86400000`
   - `CORS_ALLOWED_ORIGINS=https://your-frontend-url.onrender.com`

## Frontend

1. Create a Render **Static Site** from `frontend/`.
2. Build command:
   ```bash
   npm install && npm run build
   ```
3. Publish directory:
   ```bash
   dist
   ```
4. Add env var:
   - `VITE_API_BASE_URL=https://your-backend-url.onrender.com/api`

## Production Notes

- Use a long random `JWT_SECRET`.
- Set `spring.jpa.hibernate.ddl-auto=validate` with migrations in real production.
- Keep Swagger enabled for demo; restrict it for production.
