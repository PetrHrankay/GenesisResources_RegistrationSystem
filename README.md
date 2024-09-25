# Genesis Resources Registration System - README

This is the second project in Engeto Java Academy. It involves building a registration system for the Genesis Resources company, allowing for fair distribution of resources. The application features a **REST API** to manage users and stores data in a database.

## Application Overview

The application provides a **RESTful API** for managing users, supporting operations such as:
- **Creating new users**
- **Retrieving information about a user**
- **Listing all users**
- **Updating user details**
- **Deleting users**

Each user is assigned a **personID** from a predefined list, and a **UUID** is generated for uniqueness. The `dataPersonId.txt` file simulates a system-provided personID for each new user. All data is stored in a database.

## Additional Files

- The **Postman collection** (API test cases) and **SQL scripts** for database creation are located in the folder: `src/main/resources`.
- The **database connection settings** and other configuration strings are defined in the `DatabaseConfiguration` class.

