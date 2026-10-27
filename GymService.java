package service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import exception.GymException;
import model.*;

public class GymService {
    private final Map<Integer, Member> members = new LinkedHashMap<>();
    private final Map<String, Plan> plans = new LinkedHashMap<>();
    private final Map<Integer, Invoice> invoices = new LinkedHashMap<>();
    private final List<Payment> payments = new ArrayList<>();
    private int nextMemberId = 1;
    private int nextInvoiceId = 1001;
    private int nextPaymentId = 5001;

    public Member addMember(String name, String phone) throws GymException {
        if (name == null || name.trim().isEmpty())
            throw new GymException("Member name cannot be empty.");
        Member member = new Member(nextMemberId++, name.trim(), phone.trim());
        members.put(member.getId(), member);
        return member;
    }

    public Plan addPlan(String name, int days, double price, double taxRate, double penalty)
            throws GymException {
        if (name == null || name.trim().isEmpty())
            throw new GymException("Plan name cannot be empty.");
        if (days <= 0 || price < 0 || taxRate < 0 || penalty < 0)
            throw new GymException("Days must be positive and amounts/rates cannot be negative.");
        Plan plan = new Plan(name.trim(), days, price, taxRate, penalty);
        plans.put(name.trim().toLowerCase(), plan);
        return plan;
    }

    public Member findMember(int id) throws GymException {
        Member m = members.get(id);
        if (m == null) throw new GymException("Member ID not found.");
        return m;
    }

    public Plan findPlan(String name) throws GymException {
        Plan p = plans.get(name.trim().toLowerCase());
        if (p == null) throw new GymException("Plan not found. Add the plan first.");
        return p;
    }

    public Subscription startOrRenew(int memberId, String planName) throws GymException {
        Member member = findMember(memberId);
        Plan plan = findPlan(planName);
        LocalDate today = LocalDate.now();
        Subscription sub = member.getSubscription();

        if (sub == null) {
            sub = new Subscription(member, plan, today, today.plusDays(plan.getDurationDays() - 1L), false);
        } else {
            boolean late = today.isAfter(sub.getEndDate());
            LocalDate start = late ? today : sub.getEndDate().plusDays(1);
            sub = new Subscription(member, plan, start,
                    start.plusDays(plan.getDurationDays() - 1L), late);
        }
        member.setSubscription(sub);
        return sub;
    }

    public AttendanceRecord recordAttendance(int memberId) throws GymException {
        Member member = findMember(memberId);
        Subscription sub = member.getSubscription();
        LocalDate today = LocalDate.now();
        if (sub == null || !sub.isActiveOn(today))
            throw new GymException("Attendance denied: member has no active subscription today.");
        AttendanceRecord record = new AttendanceRecord(member, today, LocalTime.now().withNano(0));
        member.addAttendance(record);
        return record;
    }

    public Invoice generateInvoice(int memberId) throws GymException {
        Member member = findMember(memberId);
        Subscription sub = member.getSubscription();
        if (sub == null) throw new GymException("Start a subscription before generating an invoice.");
        Plan plan = sub.getPlan();
        double subtotal = plan.getPrice();
        double tax = subtotal * plan.getTaxRate() / 100.0;
        double penalty = sub.wasLateRenewal() ? plan.getLatePenalty() : 0.0;
        Invoice invoice = new Invoice(nextInvoiceId++, member, plan, sub.getStartDate(),
                sub.getEndDate(), subtotal, tax, penalty);
        invoices.put(invoice.getInvoiceId(), invoice);
        return invoice;
    }

    public Payment recordPayment(int invoiceId) throws GymException {
        Invoice invoice = invoices.get(invoiceId);
        if (invoice == null) throw new GymException("Invoice not found. Generate an invoice first.");
        if (invoice.isPaid()) throw new GymException("This invoice has already been paid.");
        invoice.markPaid();
        Payment payment = new Payment(nextPaymentId++, invoice, invoice.getTotal(), LocalDate.now());
        payments.add(payment);
        return payment;
    }

    public void displayMembers() {
        if (members.isEmpty()) {
            System.out.println("No members registered.");
            return;
        }
        for (Member m : members.values()) {
            System.out.println(m);
            System.out.println("  Attendance count: " + m.getAttendance().size());
            for (AttendanceRecord a : m.getAttendance())
                System.out.println("    " + a);
        }
    }

    public void displayPlans() {
        if (plans.isEmpty()) System.out.println("No plans available.");
        for (Plan p : plans.values()) System.out.println(p);
    }

    public void displayInvoices() {
        if (invoices.isEmpty()) System.out.println("No invoices generated.");
        for (Invoice invoice : invoices.values()) invoice.display();
    }
}
