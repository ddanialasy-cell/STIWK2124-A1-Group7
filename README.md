# STIWK2124-A1-Group7
# Accessible Reading List (ARL) - Backend API

##  Description

This project is a RESTful backend API for the Accessible Reading List (ARL) system built using Spring Boot.
It supports CRUD operations, validation, pagination, and search for managing book records.

---

##  Technologies

Java, Spring Boot, Spring Data JPA, MySQL, Maven, Postman

---

##  Setup

### 1. Clone Repository

git clone https://github.com/ddanialasy-cell/STIWK2124-A1-Group7.git
cd STIWK2124-A1-Group7

### 2. Create Database

CREATE DATABASE arl_db;

### 3. Configure (application.properties)

spring.datasource.url=jdbc:mysql://localhost:3306/arl_db
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
spring.jpa.hibernate.ddl-auto=update

### 4. Run Project

mvn spring-boot:run

Server: http://localhost:8080

##  API Endpoints

### Create

POST /api/books
Content-Type: application/json

{
  "title": "Clean Code",
  "author": "Robert C. Martin",
  "category": "Programming",
  "description": "A handbook of agile software craftsmanship"
}

Response: 201 Created

### Get All

GET /api/books?page=0&size=5

Response: 200 OK
{
  "content": [...],
  "totalElements": 12,
  "totalPages": 3,
  "size": 5,
  "number": 0
}

### Search Books (with pagination)

GET /api/books?q=clean&page=0&size=5

Response: 200 OK
{
  "content": [...],
  "totalElements": 2,
  "totalPages": 1
}

### Get by ID

GET /api/books/{id}

Response: 200 OK
Response: 404 Not Found (if book does not exist)

### Update

PUT /api/books/{id}
Content-Type: application/json

{
  "title": "Updated Title",
  "author": "Updated Author",
  "category": "Education",
  "description": "Updated description here"
}

Response: 200 OK
Response: 404 Not Found (if book does not exist)

### Delete

DELETE /api/books/{id}

Response: 204 No Content
Response: 404 Not Found (if book does not exist)

### Pagination

GET /api/books/page?page=0&size=2

---

##  Database (schema.sql)

CREATE TABLE books (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
title VARCHAR(255) NOT NULL,
author VARCHAR(255) NOT NULL,
category VARCHAR(255) NOT NULL,
description TEXT NOT NULL
);

---

##  Error Handling

Example:
{"error": "Book not found with id 999"}

Validation:
{"title": "Title is required"}

---

##  Conclusion

This API successfully implements CRUD operations with validation, pagination, search, and proper error handling using Spring Boot and MySQL.

---

## Group Member

1. Muhammad Danial Asyraf Bin Azhar 304586 
2. Tan Jun Hao 305506 
3. Ng Ee Jing 305874 
4. Hazaruddin bin Mazlan 306120 
5. Justin Abraham Unggun anak Numpang 306087 

---
