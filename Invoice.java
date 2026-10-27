package model;

import java.time.LocalDate;

public class Invoice {
    private final int invoiceId;
    private final Member member;
    private final Plan plan;
    private final LocalDate cycleStart;
    private final LocalDate cycleEnd;
    private final double subtotal;
    private final double tax;
    private final double penalty;
    private final double total;
    private boolean paid;

    public Invoice(int invoiceId, Member member, Plan plan, LocalDate cycleStart,
                   LocalDate cycleEnd, double subtotal, double tax, double penalty) {
        this.invoiceId = invoiceId;
        this.member = member;
        this.plan = plan;
        this.cycleStart = cycleStart;
        this.cycleEnd = cycleEnd;
        this.subtotal = subtotal;
        this.tax = tax;
        this.penalty = penalty;
        this.total = subtotal + tax + penalty;
        this.paid = false;
    }

    public int getInvoiceId() { return invoiceId; }
    public Member getMember() { return member; }
    public double getTotal() { return total; }
    public boolean isPaid() { return paid; }
    public void markPaid() { paid = true; }

    public void display() {
        System.out.println("\n----- INVOICE -----");
        System.out.println("Invoice ID : " + invoiceId);
        System.out.println("Member     : " + member.getName() + " (ID " + member.getId() + ")");
        System.out.println("Plan       : " + plan.getName());
        System.out.println("Cycle      : " + cycleStart + " to " + cycleEnd);
        System.out.printf("Subtotal   : Rs. %.2f%n", subtotal);
        System.out.printf("Tax        : Rs. %.2f%n", tax);
        System.out.printf("Penalty    : Rs. %.2f%n", penalty);
        System.out.printf("Total      : Rs. %.2f%n", total);
        System.out.println("Payment    : " + (paid ? "PAID" : "UNPAID"));
        System.out.println("-------------------");
    }
}
