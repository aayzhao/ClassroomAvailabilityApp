package project.classavailability.classes;

import java.lang.reflect.Array;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;

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
    private static ArrayList<DayOfWeek> parseDays(String days) {
        if (days == null) throw new IllegalArgumentException("String days cannot be a null argument");
        String[] split = days.split("((?=[A-Z]))");
        for (String day: split) {
            System.out.println(day);
        }
        return null;
    }


    public static String getDayStringFromEnum(DayOfWeek day) {
        switch (day) {
            case SUNDAY: return "Su";
            case MONDAY: return "M";
            case TUESDAY: return "Tu";
            case WEDNESDAY: return "W";
            case THURSDAY: return "Th";
            case FRIDAY: return "F";
            case SATURDAY: return "Sa";
            default: return ""; // Handle other days if necessary
        }
    }

    public static DayOfWeek getEnumFromDayString(String day) {
        switch (day) {
            case "Su": return DayOfWeek.SUNDAY;
            case "M": return DayOfWeek.MONDAY;
            case "Tu": return DayOfWeek.TUESDAY;
            case "W": return DayOfWeek.WEDNESDAY;
            case "Th": return DayOfWeek.THURSDAY;
            case "F": return DayOfWeek.FRIDAY;
            case "Sa": return DayOfWeek.SATURDAY;
            default: throw new IllegalArgumentException("Not a valid day abbreviation");
        }
    }

    // main method for testing
    public static void main(String[] args) {
        parseDays("MWF");
    }
}
