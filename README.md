# 🛒 ShopCore

> A modern eCommerce platform built on Microservices Architecture featuring user authentication, product listings, cart management, and role-based access control.

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=spring-security&logoColor=white)
![Spring Cloud](https://img.shields.io/badge/Spring_Cloud-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)

---

## 📸 Screenshots

> _Coming soon..._

---

## 📌 About

**ShopCore** is a fully distributed eCommerce backend built using the **Microservices Architecture**. Each core feature is implemented as an independent service, making the system highly scalable, maintainable, and easy to extend. Services communicate with each other via REST APIs and are managed using Spring Cloud.

---

## 🏗️ Architecture

Each feature runs as its own independent microservice:

```
ShopCore/
├── api-gateway/          # Routes requests to appropriate services
├── user-service/         # User authentication & registration
├── product-service/      # Product listings & inventory
├── cart-service/         # Cart management
└── role-service/         # Role-based access control
```

---

## ✨ Features

- 🔐 **User Authentication** — Secure login and registration with JWT-based auth via Spring Security
- 🛍️ **Product Listings** — Browse, search, and manage product catalog
- 🛒 **Cart Management** — Add, update, and remove items from cart
- 👥 **Role-Based Access Control** — Admin and customer roles with permission-based access
- 🌐 **API Gateway** — Centralized entry point for all microservices
- 🐳 **Dockerized** — Each service runs in its own Docker container

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Java |
| Framework | Spring Boot |
| Security | Spring Security + JWT |
| Microservices | Spring Cloud |
| Containerization | Docker |
| Service Discovery | Spring Cloud Eureka |
| API Gateway | Spring Cloud Gateway |

---

## 🚀 Getting Started

### Prerequisites

Make sure you have the following installed:

- Java 17+
- Docker & Docker Compose
- Maven

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/ShopCore.git
   cd ShopCore
   ```

2. **Build all services**
   ```bash
   mvn clean install
   ```

3. **Run with Docker Compose**
   ```bash
   docker-compose up --build
   ```

4. **Access the API Gateway**
   ```
   http://localhost:8080
   ```

---

## 📡 API Overview

| Service | Base URL |
|---|---|
| User Service | `/api/users` |
| Product Service | `/api/products` |
| Cart Service | `/api/cart` |
| Auth | `/api/auth` |

---

## 🔐 Authentication

ShopCore uses **JWT (JSON Web Tokens)** for authentication.

1. Register or login via `/api/auth/register` or `/api/auth/login`
2. Receive a JWT token in the response
3. Pass the token in the `Authorization` header for protected routes:
   ```
   Authorization: Bearer <your_token>
   ```

---

## 👥 Roles

| Role | Access |
|---|---|
| `ADMIN` | Full access — manage products, users, orders |
| `CUSTOMER` | Browse products, manage own cart |

---

## 🐳 Docker

Each microservice has its own `Dockerfile`. To run all services together:

```bash
docker-compose up --build
```

To stop all services:

```bash
docker-compose down
```

---

## 🤝 Contributing

Contributions are welcome! Feel free to open an issue or submit a pull request.

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin main`)
5. Open a Pull Request

---

## 📄 License

This project is licensed under the MIT License — see the [LICENSE](LICENSE) file for details.

---

<p align="center">Made with ❤️ by <strong>Vivekananda Dash</strong></p>
