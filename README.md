# BankingSystem-IronHack
This repository contains a **REST API**, created as the **Final project** for Ironhack Java bootcamp.


# Author: 
### Anastasiia Gorshantova

***

## Technology stack

<li>IntelliJ IDEA 2022.3.1 (Community Edition)</li>
<li>Java 17</li>
<li>Maven 3.8.6</li>
<li>MySQL 8.0.30</li>
<li>JUnit 5</li>
<li>Java Spring Boot</li>
<li>Spring Security</li>
<li>Java JDBC</li>
<li>Java JPA</li>

***
## Description of the project

### User information

**Admins** are in charge of creating new accounts and change the balance of any account. Admins also can create 3d Parties.

**Account holders** can be primary or secondary owners of any type of account.
Once logged in, they can access the information of their own accounts and transfer money from them to any other account in the DB.

### Account information

**Checking account** is the basic account type. In the case the account holder is younger than 24 years old, a student account will be created, meaning the account will not have a required minimum balance or a monthly maintenance fee.

**Savings account** have an interest rate.

**Credit Card account** have a minimum balance according to their credit limit. They can not make a transaction of more amount than their credit limit is.

**Penalty fees** are applied if an account's balance goes below the minimum balance.

***

## API Routes

| Method | Route                                | Description                                      |
|--------|--------------------------------------|--------------------------------------------------|
| POST   | /create/{accountType}/{id}           | Create an account of any type                    |
| POST   | /create/3dParty                      | Create 3d Party                                  |
| GET    | /checkMyBalance/{id}                 | Checking the balance of AccountHolder's account  |
| POST   | /makeTransaction                     | Create a transaction between two account holders |
| GET    | /balance/{id}                        | Checking the balance of any id (only for admins) |
| PATCH  | /changeBalance/{id}                  | Changing balance (only for admins)               |
