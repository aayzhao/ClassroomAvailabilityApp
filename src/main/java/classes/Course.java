package classes;

import java.util.*;

public class Course {
    private String courseCode; // String containing the course major code, class code, and section concatenated
    private String building;
    private String room;
    private int courseNumber; // unique course identifier number
    private List<TimeSlot> timeSlots;

    /**
     * Constructor that does not require a timeslot list, and instead a course identifier number
     * @param _courseCode   Course code composed of course major code and class + section number
     * @param _building     Building name
     * @param _room         Room number
     * @param _courseNumber Unique identifier for the course
     */
    public Course(String _courseCode, String _building, String _room, int _courseNumber) {
        this.courseCode = _courseCode;
        this.building = _building;
        this.room = _room;
        this.timeSlots = new ArrayList<>();
        this.courseNumber = _courseNumber;
    }

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

    @Override
    public boolean equals(Object other) {
        if (other == null) throw new IllegalArgumentException("Object cannot be null");
        return this.toString().equals(other.toString());
    }

    @Override
    public String toString(){
        return Integer.toString(this.courseNumber);
    }
}
