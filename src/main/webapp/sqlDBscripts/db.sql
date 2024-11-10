create database servletDB;
use servletDB;
show tables;
CREATE TABLE feedback (id INT AUTO_INCREMENT PRIMARY KEY,
email VARCHAR(255) NOT NULL,
phone VARCHAR(20) NOT NULL,
message TEXT NOT NULL);
desc feedback;