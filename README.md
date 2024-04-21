# FinTech Innovators Payment Processing Platform

## Overview

FinTech Innovators is a startup dedicated to transforming the finance industry through innovative payment processing solutions. In pursuit of this mission, we have developed a robust RESTful API using Java Spring to facilitate secure and efficient transactions. This README provides comprehensive documentation for the FinTech-API, including details on API endpoints, data models, and deployment instructions.

## Features

- **Secure Authentication**: Utilizes JSON Web Tokens (JWT) for secure authentication.
- **Transaction Handling**: Efficiently handles transaction processing.
- **Database Integration**: Integrates with a PostgreSQL database to store transaction data.
- **RESTful API**: Implements a RESTful architecture for easy integration with other systems.

## API Endpoints

### 1. Get Cards for User
- **URL**: `/api/cards/{userId}`
- **Method**: `GET`
- **Description**: Retrieves all cards associated with a specific user.
- **Request Parameters**:
  - `userId`: The ID of the user whose cards are to be retrieved.
- **Response**:
  - Status Code: `200 OK`
  - Body: List of cards with card number and balance.

### 2. Create Transaction
- **URL**: `/api/transactions`
- **Method**: `POST`
- **Description**: Creates a new transaction.
- **Request Body**:
  ```json
  {
    "sourceCardId": "string",
    "destinationCardId": "string",
    "amount": 0,
    "currency": "string",
    "fee": 0,
    "feeAmount": 0,
    "transactionType": "string"
  }
  ```
- **Response**:
  - Status Code: `201 Created`
  - Body: Details of the created transaction.

## Data Models

### 1. Card
- **Attributes**:
  - `cardNumber`: String
  - `balance`: Double

### 2. Transaction
- **Attributes**:
  - `sourceCardId`: String
  - `destinationCardId`: String
  - `amount`: Double
  - `currency`: String
  - `fee`: Double
  - `feeAmount`: Double
  - `transactionType`: String

## Deployment

### Steps
1. Clone the repository: `git clone <repository-url>`
2. Build the project: `mvn clean package`
3. Go to application.properties and do some change:
3.1 change spring.datasource.url=jdbc:postgresql://localhost:5432/fintech to your own database url
3.2 change spring.datasource.username=postgres to your username
3.3 change spring.datasource.password=57206700
5. Run the application

## Screenshots and Video Demonstration

Screenshots and a video demonstration of the API in action can be found in the `/docs` directory of this repository.
