# Capstone Project

## Overview

This project is an e-commerce web application that allows users to browse products, add them to a cart, and place orders. The application is built using Spring Boot, and it follows an MVC architecture. The main functionalities include user registration and authentication, product management, cart management, and order processing.

## Technologies Used

- Java
- Spring Boot
- Spring MVC
- Spring Data JPA
- Thymeleaf
- Hibernate
- PostgreSQL
- Maven

## Project Structure

The project is organized into several packages, each responsible for different aspects of the application:

- `com.example.capstoneproject.controllers`: Contains the controllers for handling HTTP requests.
- `com.example.capstoneproject.domain`: Contains the entity classes representing the database tables.
- `com.example.capstoneproject.services`: Contains the service classes that handle the business logic.
- `com.example.capstoneproject.services.interfaces`: Contains the interfaces for the service classes.

## Setting Up the Project

### Prerequisites

- JDK 8 or later
- Maven
- MySQL

### Steps

1. **Clone the repository**:
   ```sh
   git clone https://github.com/yourusername/capstoneproject.git
   cd capstoneproject
   ```

2. **Configure the database**:
   Create a database named `capstone` in your MySQL server and update the `application.properties` file with your database credentials.
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/capstone
   spring.datasource.username=yourusername
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update
   ```

3. **Build the project**:
   ```sh
   mvn clean install
   ```

4. **Run the application**:
   ```sh
   mvn spring-boot:run
   ```

5. **Access the application**:
   Open your browser and go to `http://localhost:8080`.

## Endpoints

### User Management

- **Registration**: `POST /users/registration`
- **Login**: `POST /users/login`
- **Logout**: `GET /users/logout`

### Product Management

- **Home Page**: `GET /`
- **Product Details**: `GET /product/{id}`

### Cart Management

- **Add Product to Cart**: `POST /carts/addproduct`
- **Increase Product Quantity**: `PUT /carts/add/{productVariantId}`
- **Decrease Product Quantity**: `PUT /carts/remove/{productVariantId}`
- **View Cart**: `GET /cart`

### Order Management

- **Create Order**: `POST /orders/create`
- **View Orders**: `GET /orders`
- **New Order Page**: `GET /orders/new`

## Key Classes and Methods

### CartController

Handles cart-related operations such as adding products to the cart and changing the quantity of products in the cart.

### FrontendController

Handles the rendering of various pages such as the home page, product details page, and cart page.

### OrderController

Handles order-related operations including creating orders and viewing order history.

### UserController

Handles user registration, login, and logout.

## Future Improvements

- Implement proper exception handling and logging.
- Add admin page for an application 
- Improve the UI/UX of the application.
- Add more detailed documentation and comments in the code.
