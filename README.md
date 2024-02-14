# BBudget
## Expense Tracking App with Receipt Recognition: Microservices Architecture

**Welcome to the repository for the backend of BBudget**

This project breaks down the backend functionality into four independent microservices, each focused on a specific task:

* **Receipt Recognition Service:** Extracts key information (date, merchant, amount, items) from uploaded receipt images using OCR technology.
* **Expense Management Service:** Stores, retrieves, and manages expense data extracted from receipts, including categorization, tagging, and reporting.
* **Notification Service:** Triggers and sends various notifications like pending receipt reminders, unusual spending alerts, and budget threshold alarms.
* **User Authentication and Authorization Service:** Handles user registration, login, access control, and authorization for secure interaction with the application.

**Getting Started:**

* Review the documentation within each microservice's directory for detailed setup, development, and deployment instructions.
* Choose your preferred programming language and framework for development. Popular options include Python (Flask/Django), Java (Spring Boot), and Node.js (Express).
* Consider containerization (Docker) for deployment to ease management and scalability.

**Contributing:**

We welcome contributions to this project! Feel free to create pull requests with bug fixes, new features, or improved documentation. Please follow the contribution guidelines outlined in the CONTRIBUTING.md file.

**License:**

This project is licensed under the MIT License (see LICENSE.md for details).

**Microservice Descriptions:**

1. **Receipt Recognition Service:**
    * This service utilizes OCR libraries/APIs (Tesseract, Google Cloud Vision) to parse text from receipt images.
    * It extracts relevant information like dates, merchants, amounts, and individual items purchased.
    * APIs are designed for consumption by other microservices and frontend clients.
    * Error handling and logging ensure reliability and traceability.

2. **Expense Management Service:**
    * Stores expense data (date, merchant, amount, category, etc.) in a suitable database (PostgreSQL, MongoDB).
    * Provides CRUD operations for managing expenses.
    * Categorizes expenses based on merchant names or user-defined tags.
    * Generates expense reports, summaries, and analytics.
    * Implements authentication and authorization for secure data access.

3. **Notification Service:**
    * Handles various notification types (email, SMS, push notifications).
    * Triggers notifications based on predefined conditions (pending receipts, irregular spending).
    * Integrates with external services (SMTP, Twilio) for sending notifications.
    * Allows user preferences for configuring notification settings.
    * Implements error handling and retry mechanisms for reliable delivery.

4. **User Authentication and Authorization Service:**
    * Manages user accounts, login sessions, and access control.
    * Implements user registration, login, and password management.
    * Integrates with secure authentication protocols (OAuth 2.0, JWT).
    * Implements role-based access control for user roles (admin, regular user).
    * Hashing and encryption protect user credentials.

We hope this comprehensive readme provides a clear overview of the project and its microservices! Feel free to explore the individual folders and documentation for in-depth details.
