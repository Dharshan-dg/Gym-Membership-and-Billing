package model;

import java.time.LocalDate;

public class Subscription {
    private final Member member;
    private final Plan plan;
    private LocalDate startDate;
    private LocalDate endDate;
    private final boolean lateRenewal;

    public Subscription(Member member, Plan plan, LocalDate startDate, LocalDate endDate) {
        this(member, plan, startDate, endDate, false);
    }

    public Subscription(Member member, Plan plan, LocalDate startDate, LocalDate endDate, boolean lateRenewal) {
        this.member = member;
        this.plan = plan;
        this.startDate = startDate;
        this.endDate = endDate;
        this.lateRenewal = lateRenewal;
    }

    public Member getMember() { return member; }
    public Plan getPlan() { return plan; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public boolean wasLateRenewal() { return lateRenewal; }

    public boolean isActiveOn(LocalDate date) {
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }

    public boolean isLateOn(LocalDate date) {
        return date.isAfter(endDate);
    }

    public void renew(LocalDate today) {
        LocalDate newStart = today.isAfter(endDate) ? today : endDate.plusDays(1);
        startDate = newStart;
        endDate = newStart.plusDays(plan.getDurationDays() - 1L);
    }
}
