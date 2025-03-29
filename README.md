
# Real-Time Event Ticketing System

This project involves developing a **Real-Time Event Ticketing System** with the **Producer-Consumer Pattern** in Java. The system consists of vendors who add tickets into a shared ticket pool, and customers who purchase the released tickets.

## Technologies Used

- **CLI**: Core Java
- **Backend**: Spring Boot
- **Frontend**: React.js
- **IDE Setup**: IntelliJ IDEA, Spring Boot Initializer, Visual Studio Code
- **Database**: MySQL Workbench

## Project Overview

The project handles multiple ticket additions and purchases with proper synchronization and configuration settings. It implements the **Producer-Consumer Pattern** using Java threads for concurrency and synchronization.

### Key Components:
- **Configuration**: Class to handle system configuration parameters.
- **Vendor**: Class to simulate the process of vendors adding tickets to the pool.
- **Customer**: Class to simulate customers purchasing tickets.
- **TicketPool**: Shared resource where tickets are stored and accessed by vendors and customers.
- **Ticket**: Class representing a ticket with various attributes.
  
The backend includes REST APIs for:
- Setting configuration parameters
- Starting and stopping the ticketing system
- Displaying customer purchases and recording vendor and ticket data

### Frontend:
The frontend is a responsive interface built with **React.js** and **Bootstrap**. It includes:
- A form for entering configuration inputs
- Buttons to start and stop the ticketing system
- A section to display customer ticket purchase details
- A form section to add vendor and ticket details.

## Features:
- Real-time ticket purchasing and addition
- Synchronization to ensure thread safety
- REST APIs to control system behavior (start, stop, configure)
- Responsive frontend for easy configuration and viewing of ticket purchases

## Tech Stack:
- **CLI**: Java
- **Backend**: Spring Boot
- **Frontend**: React.js
