package project.classavailability.classes;

import java.util.*;

public class Course {
    private String courseCode; // String containing the course major code, class code, and section concatenated
    private String building;
    private String room;
    private int courseNumber; // unique course identifier number
    private TimeSlot timeSlot;

    // TODO: implement
    /**
     * Constructor requiring all private field arguments
     * @param _courseCode   Course code composed of course major code and class + section number
     * @param _building     Building name
     * @param _room         Room number
     * @param _courseNumber Unique identifier for the course
     * @param timeSlot      TimeSlot object representing date, time together
     */
    public Course(String _courseCode, String _building, String _room, int _courseNumber, TimeSlot timeSlot) {
        this.courseCode = _courseCode;
        this.building = _building;
        this.room = _room;
        this.timeSlot = timeSlot;
        this.courseNumber = _courseNumber;
    }

    /**
     * Constructor that does not require a timeslot, and instead a course identifier number
     * @param _courseCode   Course code composed of course major code and class + section number
     * @param _building     Building name
     * @param _room         Room number
     * @param _courseNumber Unique identifier for the course
     */
    public Course(String _courseCode, String _building, String _room, int _courseNumber) {
        this.courseCode = _courseCode;
        this.building = _building;
        this.room = _room;
        this.timeSlot = null;
        this.courseNumber = _courseNumber;
    }

    public Course(String _courseCode, String _building, String _room, TimeSlot _timeSlots) {
        this.courseCode = _courseCode;
        this.building = _building;
        this.room = _room;
        this.timeSlot = _timeSlots;
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

    public TimeSlot getTimeSlot(){
        return timeSlot;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) throw new IllegalArgumentException("Object cannot be null");
        if (!(other instanceof Course)) return false;
        return this.toString().equals(other.toString());
    }

    @Override
    public String toString(){
        return Integer.toString(this.courseNumber);
    }
}
