package main;

import java.util.Scanner;
import exception.GymException;
import model.*;
import service.GymService;
import util.InputUtil;

public class GymApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputUtil input = new InputUtil(scanner);
        GymService service = new GymService();
        boolean running = true;

        while (running) {
            System.out.println("\n===== GYM MEMBERSHIP & BILLING =====");
            System.out.println("1. Add Member");
            System.out.println("2. Add Plan");
            System.out.println("3. Start or Renew Subscription");
            System.out.println("4. Record Attendance");
            System.out.println("5. Generate Invoice");
            System.out.println("6. Record Payment");
            System.out.println("7. Display Members");
            System.out.println("8. Display Plans");
            System.out.println("9. Display Invoices");
            System.out.println("0. Exit");
            int choice = input.readInt("Enter choice: ");

            try {
                switch (choice) {
                    case 1: {
                        String name = input.readLine("Enter member name: ");
                        String phone = input.readLine("Enter phone number: ");
                        Member m = service.addMember(name, phone);
                        System.out.println("Member added successfully. Member ID: " + m.getId());
                        break;
                    }
                    case 2: {
                        String name = input.readLine("Plan name: ");
                        int days = input.readInt("Duration in days: ");
                        double price = input.readDouble("Plan price (Rs.): ");
                        double tax = input.readDouble("Tax rate (%): ");
                        double penalty = input.readDouble("Late renewal penalty (Rs.): ");
                        System.out.println("Plan added: " + service.addPlan(name, days, price, tax, penalty));
                        break;
                    }
                    case 3: {
                        int id = input.readInt("Member ID: ");
                        String plan = input.readLine("Plan name: ");
                        Subscription s = service.startOrRenew(id, plan);
                        System.out.println("Subscription updated successfully.");
                        System.out.println("Status today: " +
                                (s.isActiveOn(java.time.LocalDate.now()) ? "ACTIVE" : "INACTIVE"));
                        System.out.println("Plan: " + s.getPlan().getName());
                        System.out.println("Valid from " + s.getStartDate() + " to " + s.getEndDate());
                        break;
                    }
                    case 4: {
                        int id = input.readInt("Member ID: ");
                        AttendanceRecord a = service.recordAttendance(id);
                        System.out.println("Attendance recorded for " + a.getMember().getName()
                                + " on " + a.getDate() + " at " + a.getTime());
                        break;
                    }
                    case 5: {
                        int id = input.readInt("Member ID: ");
                        Invoice invoice = service.generateInvoice(id);
                        invoice.display();
                        break;
                    }
                    case 6: {
                        int id = input.readInt("Invoice ID: ");
                        Payment p = service.recordPayment(id);
                        System.out.println("Payment recorded. Payment ID: " + p.getPaymentId());
                        System.out.printf("Amount paid: Rs. %.2f on %s%n",
                                p.getAmount(), p.getPaymentDate());
                        p.getInvoice().display();
                        break;
                    }
                    case 7:
                        service.displayMembers();
                        break;
                    case 8:
                        service.displayPlans();
                        break;
                    case 9:
                        service.displayInvoices();
                        break;
                    case 0:
                        running = false;
                        System.out.println("Thank you. Exiting Gym Membership & Billing System.");
                        break;
                    default:
                        System.out.println("Invalid menu choice. Select a listed option.");
                }
            } catch (GymException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
        scanner.close();
    }
}
