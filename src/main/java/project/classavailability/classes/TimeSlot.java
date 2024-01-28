package project.classavailability.classes;

import java.lang.reflect.Array;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TimeSlot {
    private final String daysString;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final List<DayOfWeek> days;

    public TimeSlot(String _daysString, LocalTime _startTime, LocalTime _endTime) {
        this.daysString = _daysString;
        this.startTime = _startTime;
        this.endTime = _endTime;
        this.days = parseDays(_daysString);
    }

    public String getDays() {
        return daysString;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
    public List<DayOfWeek> getDaysEnum() {
        return this.days;
    }

    private static ArrayList<DayOfWeek> parseDays(String days) {
        if (days == null) throw new IllegalArgumentException("String days cannot be a null argument");
        if (days.equals("TBA")) return new ArrayList<>();

        String[] split = days.split("((?=[A-Z]))");
        ArrayList<DayOfWeek> enumDays = new ArrayList<>();
        for (String day: split) {
            enumDays.add(getEnumFromDayString(day));
        }
        return enumDays;
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
            default: return ""; // default not necessary but here it is
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
