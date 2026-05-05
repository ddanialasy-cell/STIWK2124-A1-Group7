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

cd into desired directory and git clone https://github.com/ddanialasy-cell/STIWK2124-A1-Group7.git, and then [cd STIWK2124-A1-Group7]

### 2. Create Database

CREATE DATABASE arl_db;

### 3. Configure (application.properties)

spring.datasource.url=jdbc:mysql://localhost:3306/arl_db [Make sure localhost port is the same as MySQL port]
spring.datasource.username= [insert your MySQL username here]
spring.datasource.password= [insert your MySQL password here]

### 4. Run Project

mvn spring-boot:run

Server: http://localhost:8080

##  API Endpoints

### Create a Book

POST /api/books

{   
    "bookTitle": "Available", 
    "bookDescription": "This book fucking sucks lmao", 
    "bookCategory": "Educational", 
    "bookGenre": "Comedy", 
    "bookPublishDate": "10/10/1010"
}

### Get all Books

GET /api/books

### Get by Book ID

GET /api/books/{id}

### Update

PUT /api/books/{id}

{   
    "bookTitle": "Dead", 
    "bookDescription": "This book so fucking sucks lmao", 
    "bookCategory": "Inspring", 
    "bookGenre": "Tradegy", 
    "bookPublishDate": "11/10/1010"
}

### Delete

DELETE /api/books/{id}

### Pagination

GET /api/books/page?page=0&size=2

### Search

GET /api/books/search?bookTitle=Dead

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
