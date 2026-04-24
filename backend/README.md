  # 🧠 Sorting Visualizer — Spring Boot Backend

Migration from **Node.js + Express** → **Java Spring Boot 3 + MongoDB Atlas**

---

## 📦 Tech Stack

| Layer      | Before (MERN)      | After (Spring Boot)          |
|------------|--------------------|------------------------------|
| Language   | JavaScript (Node)  | Java 17                      |
| Framework  | Express.js         | Spring Boot 3.2              |
| Database   | MongoDB Atlas      | MongoDB Atlas (same DB!)     |
| ORM        | Mongoose           | Spring Data MongoDB          |
| Validation | express-validator  | Jakarta Bean Validation      |

---

## 🗂 Project Structure

```
src/main/java/com/sortingvisualizer/
├── SortingVisualizerApplication.java   ← Entry point (main)
├── model/
│   ├── SortingRun.java                 ← MongoDB document (@Document)
│   └── ApiResponse.java                ← Generic response wrapper
├── repository/
│   └── SortingRunRepository.java       ← MongoRepository (like Mongoose model)
├── service/
│   └── SortingRunService.java          ← Business logic layer
└── controller/
    ├── SortingRunController.java        ← REST API endpoints
    ├── CorsConfig.java                  ← CORS (replaces cors npm package)
    ├── MongoConfig.java                 ← Enables @CreatedDate auditing
    └── GlobalExceptionHandler.java      ← Replaces Express error middleware
```

---

## ⚙️ Setup

### Prerequisites
- Java 17+
- Maven 3.8+
- MongoDB Atlas account (same cluster as before)

### 1. Configure MongoDB

Edit `src/main/resources/application.properties`:

```properties
spring.data.mongodb.uri=mongodb+srv://<username>:<password>@<cluster-url>/sorting_visualizer?retryWrites=true&w=majority
```

Replace `<username>`, `<password>`, and `<cluster-url>` with your Atlas credentials.

### 2. Run the application

```bash
mvn spring-boot:run
```

Server starts on: `http://localhost:8080`

### 3. Build JAR for deployment

```bash
mvn clean package
java -jar target/sorting-visualizer-backend-1.0.0.jar
```

---

## 🔗 API Endpoints

All endpoints match the original Node.js/Express backend.

| Method   | Endpoint                        | Description                          |
|----------|---------------------------------|--------------------------------------|
| `GET`    | `/api/health`                   | Health check                         |
| `GET`    | `/api/runs`                     | Get all sorting runs ✅ (same as before) |
| `POST`   | `/api/runs`                     | Save a new sorting run ✅            |
| `GET`    | `/api/runs/{id}`                | Get a run by ID                      |
| `DELETE` | `/api/runs/{id}`                | Delete a run by ID                   |
| `GET`    | `/api/runs/recent`              | Get the 10 most recent runs          |
| `GET`    | `/api/runs/algorithm/{name}`    | Filter runs by algorithm name        |
| `DELETE` | `/api/runs`                     | Clear all run history                |

---

## 📤 Request / Response Format

### POST /api/runs — Save a sorting run

**Request Body:**
```json
{
  "algorithm": "Bubble Sort",
  "timeTaken": 123.45,
  "arraySize": 50
}
```

**Response:**
```json
{
  "success": true,
  "message": "Sorting run saved successfully",
  "data": {
    "id": "64f1a2b3c4d5e6f7a8b9c0d1",
    "algorithm": "Bubble Sort",
    "timeTaken": 123.45,
    "arraySize": 50,
    "createdAt": "2025-01-01T10:30:00"
  }
}
```

---

## 🔄 Update React Frontend

Change the base URL in your React app from:

```js
// OLD
const BASE_URL = "https://sorting-backend-sppc.onrender.com";

// NEW (local dev)
const BASE_URL = "http://localhost:8080";

// NEW (deployed)
const BASE_URL = "https://your-spring-boot-server.com";
```

---

## ☁️ Deployment Options

- **Render** — same as before, deploy as a JAR
- **Railway** — supports Java/Maven natively
- **AWS Elastic Beanstalk** — Java platform
- **Docker** — add Dockerfile, deploy anywhere

---

## 🛠 Key Differences from Node.js Version

| Node.js / Express          | Spring Boot Equivalent           |
|----------------------------|----------------------------------|
| `require('mongoose')`      | `spring-boot-starter-data-mongodb` |
| `mongoose.Schema`          | `@Document` + `@Id` on class    |
| `Model.find()`             | `repository.findAll()`           |
| `new Model(data).save()`   | `repository.save(entity)`        |
| `app.use(cors())`          | `CorsFilter` @Bean               |
| `app.use(express.json())`  | Built-in (no config needed)      |
| `res.status(201).json()`   | `ResponseEntity.status(201).body()` |
| `try/catch` in each route  | `@RestControllerAdvice` globally |
