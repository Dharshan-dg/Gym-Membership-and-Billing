package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class AttendanceRecord {
    private final Member member;
    private final LocalDate date;
    private final LocalTime time;

    public AttendanceRecord(Member member, LocalDate date, LocalTime time) {
        this.member = member;
        this.date = date;
        this.time = time;
    }

    public Member getMember() { return member; }
    public LocalDate getDate() { return date; }
    public LocalTime getTime() { return time; }

    @Override
    public String toString() {
        return date + " at " + time;
    }
}
