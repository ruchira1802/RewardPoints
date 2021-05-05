# RewardPoints
A simple application to store and spend reward points.
A user can receive Reward points from multiple payers. Each transaction is stored with the payer name, points and the transaction time.
A user can also spend those points, however the user won't be deciding which payer to deduct the points from.
The rules for determining that are:
  The oldest point is spent first
  No payer's point can be negative after spending


This repository contains the backend code for the application.
The tech stack is:
Java, Spring Boot and maven as the build tool

This application has the REST APIs for:

1. Storing the transaction(In memory)

2. Storing list of transactions

3. Spending points of a user and returning the points spent from each payer

4. Fetching the balance from each payer


