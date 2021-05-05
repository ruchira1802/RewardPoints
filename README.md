# RewardPoints
A simple application to store and spend reward points. <br />
 <br />
A user can receive Reward points from multiple payers. Each transaction is stored with the payer name, points and the transaction time. <br />
A user can also spend those points, however the user won't be deciding which payer to deduct the points from. <br />
The rules for determining that are: <br />
  \bullet The oldest point is spent first <br />
  No payer's point can be negative after spending


This repository contains the backend code for the application. <br />
The tech stack is: <br />
Java, Spring Boot and maven as the build tool <br />

This application has the REST APIs for: <br />

1. Storing the transaction(In memory)

2. Storing list of transactions

3. Spending points of a user and returning the points spent from each payer

4. Fetching the balance from each payer


