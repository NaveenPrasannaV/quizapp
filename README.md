# 🧠 Quiz Application - Microservices Architecture

A **distributed Quiz Application** built using **Spring Boot Microservices**, implementing service discovery, API Gateway, and inter-service communication.

---

## 🚀 Tech Stack

* Java
* Spring Boot
* Spring Cloud
* Eureka (Service Registry)
* Spring Cloud Gateway
* REST APIs
* Maven

---

## 🏗️ Architecture Overview

![Image](https://miro.medium.com/1%2Ae-tofjgSqic130AYtY91zg.png)

![Image](https://miro.medium.com/1%2AmyEDAh-eH3Ecwfnw-N4a_Q.png)

![Image](https://i.postimg.cc/6pPw1ymF/spring.jpg)

![Image](https://developer.okta.com/assets-jekyll/blog/spring-cloud-gateway/spring-cloud-gateway-oauth2-e9c8cd41051bf845687ad6a56ba0d7d137b3d89e70b0e945432a7c242d0ce168.png)

---

## 🔧 Microservices

### 1️⃣ api-gateway

* Entry point for all client requests
* Routes traffic to appropriate services
* Implements load balancing

---

### 2️⃣ service-registry

* Eureka Server for service discovery
* All services register here
* Enables dynamic communication

---

### 3️⃣ question-service

* Handles quiz questions
* CRUD operations
* Category-based filtering

---

### 4️⃣ quizapp

* Core application logic
* Integrates all services
* Manages quiz flow

---

## 🔄 Flow of Request

1. Client sends request → API Gateway
2. API Gateway routes request → Service
3. Service registers/discovers via Eureka
4. Response sent back → Client

---

## ▶️ How to Run

### Step 1: Start Service Registry

```
cd service-registry
mvn spring-boot:run
```

### Step 2: Start Microservices

```
cd question-service
mvn spring-boot:run
```

```
cd quizapp
mvn spring-boot:run
```

```
cd api-gateway
mvn spring-boot:run
```

---

## 🌐 Ports (Example)

| Service          | Port |
| ---------------- | ---- |
| service-registry | 8761 |
| question-service | 8081 |
| quizapp          | 8082 |
| api-gateway      | 8765 |

---

## 🎯 Key Features

* Microservices architecture
* Service discovery using Eureka
* API Gateway routing
* Scalable and loosely coupled design
* REST-based communication

---

## 📌 Future Enhancements

* Authentication (JWT / OAuth2)
* Circuit Breaker (Resilience4j)
* Distributed tracing (Zipkin)
* Docker & Kubernetes deployment

---

## 👨‍💻 Author

Developed as part of microservices learning and backend engineering practice.
