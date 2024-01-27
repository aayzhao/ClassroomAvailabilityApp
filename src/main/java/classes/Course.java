package classes;

import java.util.*;

public class Course {
    private String courseCode;
    private String building;
    private String room;
    private List<TimeSlot> timeSlots;

    public Course(String _courseCode, String _building, String _room, List<TimeSlot> _timeSlots) {
        this.courseCode = _courseCode;
        this.building = _building;
        this.room = _room;
        this.timeSlots = _timeSlots;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getBuilding() {
        return building;
    }

    public String getRoom() {
        return room;
    }

    public List<TimeSlot> getTimeSlot(){
        return timeSlots;
    }
}
