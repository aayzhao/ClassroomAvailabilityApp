package project.classavailability.classes;


import java.util.HashSet;

public class Course {
    private static final HashSet<Integer> courseNumbers = new HashSet<>();
    private String courseCode; // String containing the course major code, class code, and section concatenated
    private String building;
    private String room;
    private int courseNumber; // unique course identifier number
    private TimeSlot timeSlot;
    private String buildingRoom; // concatenation of building name and room

    // TODO: add input validation, i.e. check for null and throw illegalargument exception
    /**
     * Constructor requiring all private field arguments
     * @param _courseCode   Course code composed of course major code and class + section number
     * @param _building     Building name
     * @param _room         Room number
     * @param _courseNumber Unique identifier for the course
     * @param timeSlot      TimeSlot object representing date, time together
     */
    public Course(String _courseCode, String _building, String _room, int _courseNumber, TimeSlot timeSlot) {
        if (!courseNumbers.add(_courseNumber)) throw new IllegalArgumentException("Duplicate course number");
        this.courseCode = _courseCode;
        this.building = _building;
        this.room = _room;
        this.timeSlot = timeSlot;
        this.courseNumber = _courseNumber;
        this.buildingRoom = _building + " Rm " + _room;
    }

    // TODO: add input validation, i.e. check for null and throw illegalargument exception
    /**
     * Constructor that does not require a timeslot, and instead a course identifier number
     * @param _courseCode   Course code composed of course major code and class + section number
     * @param _building     Building name
     * @param _room         Room number
     * @param _courseNumber Unique identifier for the course
     */
    public Course(String _courseCode, String _building, String _room, int _courseNumber) {
        if (!courseNumbers.add(_courseNumber)) throw new IllegalArgumentException("Duplicate course numbers");
        this.courseCode = _courseCode;
        this.building = _building;
        this.room = _room;
        this.timeSlot = null;
        this.courseNumber = _courseNumber;
        this.buildingRoom = _building + " Rm " + _room;
    }

    @Deprecated
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

    /**
     * Returns the TimeSlot object associated with this class.
     * If any fields about the class are TBA then it will return a null value
     * @return  TimeSlot object of this course
     */
    public TimeSlot getTimeSlot(){
        return timeSlot;
    }

    /**
     * Returns the concatenated string of building name and room
     * @return  String representing the building and room the class is held in.
     */
    public String getLocation() {
        return buildingRoom;
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

    public static void main(String[] args) {
        Course c1 = new Course("", "", "", 1);
        Course c2 = new Course("", "", "", 2);
        Course c3 = new Course("", "", "", 3);
    }
}
