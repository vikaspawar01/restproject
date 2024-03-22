User Management REST API

The User Management REST API is a robust solution for managing user data, addresses, and blog posts. It offers CRUD (Create, Read, Update, Delete) operations for seamless interaction with the system.

Features

Create User: Create new user records with relevant information.
Retrieve User: Retrieve user records by ID or based on certain criteria.
Update User: Update existing user records with new information.
Delete User: Permanently delete user records from the system.
Address Management: CRUD operations for managing user addresses.
Blog Management: CRUD operations for managing user blog posts.
Endpoints

GET /users: Retrieve all users.
GET /users/{id}: Retrieve details of a specific user.
POST /users: Create a new user.
PUT /users/{id}: Update an existing user.
DELETE /users/{id}: Delete a user.
GET /users/{id}/address: Retrieve user address.
POST /users/{id}/address: Add a new address for a user.
PUT /users/{id}/address: Update user address.
DELETE /users/{id}/address: Delete user address.
GET /users/{id}/blogs: Retrieve all blogs associated with a user.
GET /users/{id}/blogs/{blog_id}: Retrieve a specific blog associated with a user.
POST /users/{id}/blogs: Create a new blog for a user.
PUT /users/{id}/blogs/{blog_id}: Update an existing blog associated with a user.
DELETE /users/{id}/blogs/{blog_id}: Delete a blog associated with a user.
Technologies Used

Spring Boot
Spring Data JPA
Hibernate
MySQL Database
Security

Authentication: Implement authentication mechanisms to ensure authorized access.
Authorization: Implement role-based access control to restrict endpoints.
Testing

Unit Testing: Ensure correctness of individual components.
Integration Testing: Verify interactions between components.
Deployment

Containerization: Package application into Docker containers.
Cloud Deployment: Deploy to cloud platforms for scalability.
Documentation

API Documentation: Provide detailed documentation for endpoints and usage.
