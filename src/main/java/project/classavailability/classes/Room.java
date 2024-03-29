package project.classavailability.classes;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * This class encapsulates the data surrounding a room.
 */
public class Room {
    private final static HashSet<String> rooms = new HashSet<>();
    private String building;
    private String roomNumber;
    private List<Course> schedule;

    private String buildingRoom; // concatenation of building and room number

    // TODO: add more input validation, i.e. check for null and throw illegalargument exception
    /**
     * 2 arg constructor for room. Initializes room to
     * @param building      Name of the building the room  is located in
     * @param roomNumber    Room number/code associated with said room
     */
    public Room(String building, String roomNumber) {
        if (!rooms.add(building + " Rm " + roomNumber)) throw new IllegalArgumentException("Room already exists");
        this.building = building;
        this.roomNumber = roomNumber;
        schedule = new ArrayList<>();
        buildingRoom = building + " Rm " + roomNumber;
    }

    // TODO: add more input validation, i.e. check for null and throw illegalargument exception
    /**
     * 3 arg constructor for a room
     * @param building      Name of the building room is located in
     * @param roomNumber    Room number/code associated with said room
     * @param schedule      List of courses held in this room
     */
    public Room(String building, String roomNumber, List<Course> schedule) {
        if (!rooms.add(building + " Rm " + roomNumber)) throw new IllegalArgumentException("Room already exists");
        this.schedule = schedule;
        this.building = building;
        this.roomNumber = roomNumber;
        buildingRoom = building + " Rm " + roomNumber;
    }

    /**
     * Determines if this room object is occupied at a given day and time.
     * @param day   Day of the week to check for
     * @param time  Time of day to check for (24-hour clock)
     * @return      true if occupied, false otherwise
     */
    public boolean isRoomOccupied(DayOfWeek day, LocalTime time) {
        if  (day == null || time == null) throw new IllegalArgumentException("Arguments cannot be null");
        for (Course course : schedule) {
            if (course.getTimeSlot().getDaysEnum().contains(day)) {
                if (!time.isBefore(course.getTimeSlot().getStartTime()) && !time.isAfter(course.getTimeSlot().getEndTime())) return true;
            }
        }
        return false;
    }

    // TODO: implement check for time conflicts
    /**
     * Attempts to add a course to this room's schedule.
     * @param course    Course object to be added
     * @return          Returns false if it failed, else returns true
     */
    public boolean addCourse(Course course) {
        if (schedule.contains(course)) return false;
        else {
            schedule.add(course);
            return true;
        }
    }

    @Override
    public String toString() {
        return buildingRoom;
    }
}
