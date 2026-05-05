# 🛍️ Product Service — ShopCore E-Commerce Platform

A Spring Boot Microservice responsible for managing the product catalog in the ShopCore e-commerce platform. It handles categories, sub-categories, products, brands, sizes, and images — and is registered with a Eureka discovery server for inter-service Communication.

---

## 📦 Tech Stack

| Technology | Purpose |
|---|---|
| Java 17+ | Core language |
| Spring Boot | Application framework |
| Spring Data JPA | ORM / database layer |
| MySQL | Relational database |
| Hibernate | JPA implementation |
| Spring Cloud Netflix Eureka | Service discovery (client) |
| Spring Cloud OpenFeign | Declarative HTTP client |
| Lombok | Boilerplate reduction |
| Maven | Build tool |

---

## 🗂️ Project Structure

```
product-service/
├── src/main/java/com/productservice/
│   ├── ProductServiceApplication.java   # Entry point
│   ├── controller/
│   │   └── ProductController.java       # REST endpoints
│   ├── entity/
│   │   ├── Category.java
│   │   ├── SubCategory.java
│   │   ├── Product.java
│   │   ├── Brand.java
│   │   ├── Size.java
│   │   └── Image.java
│   ├── dto/
│   │   ├── CategoryDto.java
│   │   ├── SubCategoryDto.java
│   │   ├── ProductDto.java
│   │   ├── BrandDto.java
│   │   ├── SizeDto.java
│   │   ├── ImageDto.java
│   │   └── ApiResponse.java
│   ├── repository/
│   │   ├── CategoryRepository.java
│   │   └── ProductRepository.java
│   ├── service/
│   │   ├── CategoryService.java
│   │   ├── CategoryServiceImpl.java
│   │   ├── ProductService.java
│   │   └── ProductServiceImpl.java
│   └── mapper/
│       ├── CategoryMapper.java
│       └── ProductMapper.java
└── src/main/resources/
    └── application.properties
```

---

## 🗃️ Entity Relationship Overview

The data model follows a strict top-down hierarchy:

```
Category
  └── SubCategory  (1:N)
        └── Product  (1:N)
              └── Brand  (1:N)
                    ├── Size   (1:N)
                    └── Image  (1:N)
```

| Entity | Table | Description |
|---|---|---|
| `Category` | `category` | Top-level grouping (e.g. Electronics, Fashion) |
| `SubCategory` | `sub_category` | Child of Category (e.g. Phones, T-Shirts) |
| `Product` | `products` | Belongs to a SubCategory (e.g. iPhone, Nike Tee) |
| `Brand` | `brand` | A brand variant of a product with a price |
| `Size` | `size` | Size + quantity Stock for a brand variant |
| `Image` | `images` | Image URLs associated with a brand variant |

---

## 🔗 API Endpoints

Base URL: `http://localhost:8082/api/v1/product`

### Get All Categories

```
GET /list/categories
```

**Response:**
```json
{
  "message": "All Categories Data fetched",
  "status": 200,
  "data": [
    { "id": 1, "name": "Electronics" },
    { "id": 2, "name": "Fashion" }
  ]
}
```

---

### Search Products

```
GET /list/search?keyword={keyword}
```

Searches across product name, sub-category name, and brand name (case-insensitive).

**Query Params:**

| Param | Type | Required | Description |
|---|---|---|---|
| `keyword` | String | Yes | Search term |

**Response:**
```json
{
  "message": "All Data fetched",
  "status": 200,
  "data": [
    {
      "id": 1,
      "name": "iPhone 15",
      "subCategoryId": 3,
      "brands": [
        {
          "id": 1,
          "name": "Apple",
          "price": 79999.00,
          "sizes": [...],
          "images": [...]
        }
      ]
    }
  ]
}
```

---

## ⚙️ Configuration

Located at `src/main/resources/application.properties`:

```properties
spring.application.name=product-service
server.port=8082

# MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/shopcore_product_db?createDatabaseIfNotExist=true
spring.datasource.username=<your-username>
spring.datasource.password=<your-password>
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Eureka
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
eureka.client.fetch-registry=true
eureka.client.register-with-eureka=true
eureka.instance.prefer-ip-address=true
```

> ⚠️ Do not commit real credentials. Use environment variables or a secrets manager in production.

---

## 🏃 Running the Service

### Prerequisites

- Java 17+
- MySQL running on port `3306`
- Eureka Server running on port `8761`

### Steps

```bash
# 1. Clone the repository
git clone https://github.com/your-org/product-service.git
cd product-service

# 2. Update credentials in application.properties

# 3. Build the project
mvn clean install

# 4. Run the application
mvn spring-boot:run
```

The service will start on **port 8082** and auto-register with Eureka.

---

## 🧱 Architecture — Request Flow

```
HTTP Client
     │
     ▼
ProductController  (/api/v1/product)
     │
     ├──► CategoryServiceImpl ──► CategoryMapper ──► CategoryRepository ──► MySQL
     │
     └──► ProductServiceImpl  ──► ProductMapper  ──► ProductRepository  ──► MySQL
                                                         (JPQL search)
     │
     ▼
ApiResponse<T> { message, status, data }
```

---

## 🔍 JPQL Search Query

The product search uses a custom JPQL query that performs a case-insensitive `LIKE` match across three fields:

```java
@Query("""
    SELECT DISTINCT p FROM Product p
    LEFT JOIN p.brands b
    WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
       OR LOWER(p.subCategory.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
       OR LOWER(b.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
""")
List<Product> searchProducts(@Param("keyword") String keyword);
```

`SELECT DISTINCT` prevents duplicate products when a product has multiple matching brands.

---

## 🔮 Planned / Stub Methods

The following service methods are declared but not yet implemented:

| Method | Interface | Status |
|---|---|---|
| `findByCategoryId(Long id)` | `CategoryService` | ⏳ Stub — returns `null` |
| `findByCategoryName(String name)` | `CategoryService` | ⏳ Stub — returns `null` |

---

## 🌐 Service Discovery

This service is registered as `product-service` with the Eureka discovery server. Other microservices (e.g. Order Service, Cart Service) can call it using the service name via Feign clients:

```java
@FeignClient(name = "product-service")
public interface ProductClient {
    @GetMapping("/api/v1/product/list/search")
    ApiResponse<List<ProductDto>> searchProducts(@RequestParam String keyword);
}
```

---

## 📝 Notes

- The `quantity` field in `Size` is typed as `String` — consider changing to `Integer` for stock management logic.
- `ImageDto` contains a field `imageId` which appears to be a typo; it should likely be `brandId` to match the FK on the `Image` entity.
- Error responses currently use HTTP `500 INTERNAL_SERVER_ERROR` for empty results — consider returning `204 No Content` or `404 Not Found` for semantic correctness.

---

## 📄 License

This project is part of the ShopCore platform. All rights reserved.
