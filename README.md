# Willow Link – QuickChat Application

## Programming 1A Portfolio of Evidence

**Student Number:** ST10507695  
**Module:** Programming 1A  
**Module Code:** PROG5121  
**Year:** 2026  

---

## Project Overview

Willow Link is a Java console-based communication application developed for the Programming 1A Portfolio of Evidence.

The application includes user registration and login functionality and a QuickChat messaging system. Users can create, send, disregard, store, search and delete messages.

The project was developed in Java using Apache NetBeans and Maven.

---

## Technologies Used

- Java
- Apache NetBeans
- Maven
- JUnit 5
- Gson
- JSON
- Git
- GitHub
- GitHub Actions

---

## Part 1 – Registration and Login

Part 1 implements user registration and login.

The application validates:

- Username
- Password complexity
- South African cellphone number
- Login username and password

### Username Requirements

The username must:

- Contain an underscore `_`
- Be no more than five characters long

Example:

`kyl_1`

### Password Requirements

The password must contain:

- At least eight characters
- A capital letter
- A number
- A special character

Example:

`Ch&&sec@ke99!`

### Cellphone Number

The cellphone number must contain the South African international country code.

Example:

`+27838968976`

---

## Part 2 – QuickChat Messaging

After successfully logging in, the user can access QuickChat.

The main menu contains:

1. Send Messages
2. Show recently sent messages
3. Quit
4. Stored Messages

The user specifies how many messages they would like to enter.

Each message contains:

- A unique 10-digit Message ID
- Message number
- Recipient cellphone number
- Message text
- Message Hash

Messages are limited to 250 characters.

### Message Hash

The application automatically creates a message hash using:

- First two digits of the Message ID
- Message number
- First word of the message
- Last word of the message

Example:

`00:0:HITONIGHT`

### Message Actions

The user can choose to:

1. Send the message
2. Disregard the message
3. Store the message

Stored messages are saved in:

`stored_messages.json`

Gson is used to convert Java message objects to JSON.

---

## Part 3 – Stored Message Management

Part 3 extends the QuickChat application with message management functionality.

The application uses ArrayLists to manage:

- Sent messages
- Disregarded messages
- Stored messages
- Message hashes
- Message IDs

The Stored Messages menu allows the user to:

1. Display all stored messages
2. Display the longest stored message
3. Search for a message using the Message ID
4. Search for messages using the recipient
5. Delete a message using the Message Hash
6. Display a stored message report

When a stored message is deleted, the JSON storage is updated.

---

## Automated Testing

JUnit 5 is used to test the application.

Tests include:

- Username validation
- Password validation
- Cellphone number validation
- Login functionality
- Message ID validation
- Recipient validation
- Message length validation
- Message Hash creation
- Sending messages
- Disregarding messages
- Storing messages
- Searching messages
- Finding the longest stored message
- Displaying stored message reports

The completed project currently runs **26 automated tests successfully with 0 failures and 0 errors**.

---

## GitHub Actions

GitHub Actions is configured to automatically build and test the Maven project.

The workflow file is located at:

`.github/workflows/TestJava.yml`

The automated workflow runs the Maven tests whenever the configured GitHub workflow is triggered.

---

## Running the Application

1. Open the project in Apache NetBeans.
2. Allow Maven to load the project dependencies.
3. Open `Willow_Link.java`.
4. Run the project.
5. Register a user.
6. Log in using the registered username and password.
7. Enter the number of messages you would like to create.
8. Use the QuickChat menu to manage messages.

---

## Project Structure

```text
Willow_Link
│
├── .github
│   └── workflows
│       └── TestJava.yml
│
├── src
│   ├── main
│   │   └── java
│   │       └── com.mycompany.willow_link
│   │           ├── Login.java
│   │           ├── Message.java
│   │           └── Willow_Link.java
│   │
│   └── test
│       └── java
│           └── com.mycompany.willow_link
│               ├── LoginTest.java
│               └── MessageTest.java
│
├── pom.xml
├── stored_messages.json
└── README.md
