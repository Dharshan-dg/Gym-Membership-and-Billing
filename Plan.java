package model;

public class Plan {
    private String name;
    private int durationDays;
    private double price;
    private double taxRate;
    private double latePenalty;

    public Plan(String name, int durationDays, double price, double taxRate, double latePenalty) {
        this.name = name;
        this.durationDays = durationDays;
        this.price = price;
        this.taxRate = taxRate;
        this.latePenalty = latePenalty;
    }

    public String getName() { return name; }
    public int getDurationDays() { return durationDays; }
    public double getPrice() { return price; }
    public double getTaxRate() { return taxRate; }
    public double getLatePenalty() { return latePenalty; }

    @Override
    public String toString() {
        return name + " (" + durationDays + " days, Rs. " + String.format("%.2f", price) + ")";
    }
}
