# Gym Membership & Billing System

## Problem Statement
Design and implement a Java console application for a Gym Membership & Billing system that manages memberships, plans, attendance, payments, and renewals. The application demonstrates object-oriented principles and maintains membership status and dues.

## Requirements
Classes: Member, Plan, Subscription, AttendanceRecord, Invoice, Payment, Trainer.
Business rules: active subscription required for attendance; subscription dates update during start/renewal; invoice required before payment; plans can define a late renewal penalty; attendance records link to member and date.

## How to run in Eclipse
1. Create a Java project named `Gym_Membership_Billing`.
2. Copy the folders under `src` into the project's `src` folder, preserving package names.
3. Ensure the project uses Java 8 or newer.
4. Open `main/GymApplication.java` and choose **Run As > Java Application**.

## Compile/run from terminal
From the project directory:
```bash
javac -d out src/model/*.java src/service/*.java src/exception/*.java src/util/*.java src/main/*.java
java -cp out main.GymApplication
```

## Example test flow
1. Add a member (e.g., Arun, 9876543210).
2. Add a plan (e.g., Monthly, 30 days, price 1500, tax 18%, penalty 200).
3. Start/renew the member subscription using the member ID and plan name.
4. Record attendance.
5. Generate an invoice and note its invoice ID.
6. Record payment using that invoice ID.
7. Display members and invoices.

## Student details
Name: DHARSHAN GANESH J 
Roll No: 717825I310
GitHub Repository: [Paste your GitHub repository URL after uploading]
