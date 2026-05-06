# STIWK2124-A1-Group7
# Accessible Reading List (ARL) - Backend API

##  Description

This project is a RESTful backend API for the Accessible Reading List (ARL) system built using Spring Boot.
It supports CRUD operations, validation, pagination, and search for managing book records.

---

##  Technologies

Java, Spring Boot, Spring Data JPA, MySQL, Maven, Postman

---
## Note
This assignment focuses on backend only.

The `arlfrontend` folder is optional and not required for Assignment 1.
Only the backend (`arlbackend`) should be used for testing.

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

{
  "title": "Clean Code",
  "author": "Dr Pro Gamming",
  "category": "Programming",
  "description": "A handbook of useful agile software craftsmanship"
}

Response: 201 Created

### Get All

GET /api/books

Response: 200 OK

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

{
  "title": "Updated Book Title",
  "author": "Updated Author",
  "category": "Education",
  "description": "Updated book description here"
}

Response: 200 OK
Response: 404 Not Found (if book does not exist)

### Delete

DELETE /api/books/{id}

Response: 204 No Content
Response: 404 Not Found (if book does not exist)

### Search & Pagination

GET /api/books/page?q=clean&page=0&size=2

{
  "content": [...],
  "totalElements": 2,
  "totalPages": 1
}

Response: 200 OK

> [!NOTE]
> -page: The page index you want to view (starts at 0 for the first page).  
> -size: The number of books per page (e.g., size=2 returns two books).  
> -q (Optional): A search keyword to filter books by title.

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

## Group Member

1. Muhammad Danial Asyraf Bin Azhar 304586 
2. Tan Jun Hao 305506 
3. Ng Ee Jing 305874 
4. Hazaruddin bin Mazlan 306120 
5. Justin Abraham Unggun anak Numpang 306087 

---

##  Conclusion

This API successfully implements CRUD operations with validation, pagination, search, and proper error handling using Spring Boot and MySQL.

---
