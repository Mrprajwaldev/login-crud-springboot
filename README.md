# Spring Boot Login CRUD Application

## 📌 Overview
This project is a Role-Based Login and User Management System built using Spring Boot, Thymeleaf, and MySQL.  
It allows users to register and login, and provides separate dashboards for Admin and Normal Users.

## 🚀 Features
- User Registration
- Secure Login System
- Role-Based Authentication (ADMIN / USER)
- Admin Dashboard:
  - View all users
  - Update user details
  - Change user role
  - Delete users
- User Dashboard with personalized welcome message

## 🛠️ Tech Stack
- Java
- Spring Boot
- Spring MVC
- Spring Data JPA
- Thymeleaf
- MySQL
- HTML & CSS
- Git & GitHub

## 🗂️ Database Structure

Table: user

- id (Primary Key)
- name
- email
- password
- role

## 🔐 Role Logic
- If role = ADMIN → Redirect to Admin Dashboard
- If role = USER → Redirect to User Dashboard

## 📷 Future Improvements
- Password encryption using BCrypt
- Spring Security integration
- Search & Pagination
- Profile management
- UI improvements

## 👨‍💻 Author
Prajwal Gowda
