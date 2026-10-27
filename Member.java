package model;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private int id;
    private String name;
    private String phone;
    private final List<AttendanceRecord> attendance = new ArrayList<>();
    private Subscription subscription;

    public Member(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public Subscription getSubscription() { return subscription; }
    public void setSubscription(Subscription subscription) { this.subscription = subscription; }
    public List<AttendanceRecord> getAttendance() { return attendance; }
    public void addAttendance(AttendanceRecord record) { attendance.add(record); }

    @Override
    public String toString() {
        String status = subscription != null && subscription.isActiveOn(java.time.LocalDate.now())
                ? "ACTIVE" : "INACTIVE";
        String validity = subscription == null ? "No subscription" :
                subscription.getStartDate() + " to " + subscription.getEndDate();
        return "ID: " + id + " | Name: " + name + " | Phone: " + phone +
                " | Status: " + status + " | Validity: " + validity;
    }
}
