# 🚀 Sorting Visualizer (Full Stack)

A **full-stack web application** to visualize sorting algorithms in real-time with backend storage for run history.

---

## 🌐 Live Demo

* 🔗 **Frontend (Vercel):** https://sorting-visualizer-ot41.vercel.app
* 🔗 **Backend (Render):** https://sorting-visualizer-1-71xi.onrender.com

---

## 🧠 Features

* 🎨 Visualize sorting algorithms in real-time
* ⚡ Interactive controls (speed, new array, start sorting)
* 📊 Backend-powered history of sorting runs
* 🌍 Fully deployed (Frontend + Backend)
* 🔄 REST API integration

---

## 🛠️ Tech Stack

### Frontend

* React.js
* CSS / Tailwind 
* Axios

### Backend

* Java Spring Boot
* REST APIs
* CORS Configuration

### Database

* MongoDB 

### Deployment

* Vercel (Frontend)
* Render (Backend)
* Docker 

---

## 📁 Project Structure

```
sorting-visualizer/
│
├── frontend/        # React App
│
├── backend/         # Spring Boot API
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── model/
│   └── config/
│
└── README.md
```

---

## ⚙️ Environment Variables

### Frontend (.env)

```
REACT_APP_API_URL=https://sorting-visualizer-1-71xi.onrender.com
```

### Backend

```
MONGO_URI=your_mongodb_connection_string
```

---

## 🚀 How to Run Locally

### 1. Clone repo

```
git clone https://github.com/your-username/sorting-visualizer.git
cd sorting-visualizer
```

### 2. Run Backend

```
cd backend
./mvnw spring-boot:run
```

### 3. Run Frontend

```
cd frontend
npm install
npm start
```

---

## 🔗 API Endpoints

| Method | Endpoint  | Description           |
| ------ | --------- | --------------------- |
| GET    | /api/runs | Fetch sorting history |
| POST   | /api/runs | Save sorting run      |

---

---

## 👨‍💻 Author

**Nishank Mukhija**


## ⭐ Give a Star

If you found this project useful, please ⭐ the repo!
