package project.classavailability.classes;

import java.time.LocalTime;

public class TimeSlot {
    private String days;
    private LocalTime startTime;
    private LocalTime endTime;

    public TimeSlot(String _days, LocalTime _startTime, LocalTime _endTime) {
        this.days = _days;
        this.startTime = _startTime;
        this.endTime = _endTime;
    }

    public String getDays() {
        return days;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
}
