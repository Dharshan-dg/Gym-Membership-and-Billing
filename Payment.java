package model;

import java.time.LocalDate;

public class Payment {
    private final int paymentId;
    private final Invoice invoice;
    private final double amount;
    private final LocalDate paymentDate;

    public Payment(int paymentId, Invoice invoice, double amount, LocalDate paymentDate) {
        this.paymentId = paymentId;
        this.invoice = invoice;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    public int getPaymentId() { return paymentId; }
    public Invoice getInvoice() { return invoice; }
    public double getAmount() { return amount; }
    public LocalDate getPaymentDate() { return paymentDate; }
}
