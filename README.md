E-Commerce Web Application Testing (Automation)

📌 Project Overview

This project automates the testing of a Demo Webshop Web Application. The automation framework validates core features such as Login, Registration, Password Recovery, and Cart functionality to ensure correctness, usability, and reliability.

The project demonstrates:
  - Page Object Model (POM) for better test maintainability.
  - Data-Driven Testing with database integration for input variations.
  - Selenium with Java + TestNG for automation execution.

🚀 Features Tested (Automation Coverage)
- Authentication
  - Login Page: Valid login, invalid login, blank inputs, invalid email format.
  - Registration Page: Valid registration flow, field validations, duplicate email handling, and invalid data check.
  - Password Recovery: Valid recovery, invalid/blank email scenarios, and error validation.

- Cart Functionality
  - Add single & multiple products to the cart.
  - Validate product details (name, price, quantity, total).
  - Update quantity and validate recalculated totals.
  - Remove items from the cart.
  - Apply invalid/empty discount or gift card codes → appropriate error messages.

 🛠️ Tech Stack
- Programming Language: Java
- Automation Tool: Selenium WebDriver
- Testing Framework: TestNG
- Framework Design: Page Object Model (POM)
- Test Data: Data-Driven Testing with Database Integration (JDBC)

⚙️ Prerequisites

Before running the tests, ensure you have the following installed:
- Java JDK 8 or above
- TestNG (installed in your IDE or via jar)
- Selenium WebDriver JARs included in the project
- Database Connection (configured inside test classes for fetching test data)

📌 Future Enhancements
- Extend coverage to the Checkout & Order Confirmation flow.
- Introduce cross-browser testing support.
- Integrate with Jenkins CI/CD pipeline for automated execution.

NOTE: 
“All SQL queries used for data-driven testing are stored in queries.properties. No sensitive credentials are included; database credentials must be configured via environment variables.”

👨‍💻 Author

Shubhankar S. Gaikwad


