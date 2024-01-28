package project.classavailability.classes;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ClassroomScheduleManager implements ClassroomScheduleInterface {
    private List<Course> courses;

    public ClassroomScheduleManager(List<Course> courses) {
        this.courses = courses;
    }

    // TODO: implementation needed
    @Override
    public List<String> findFreeRooms(String building, DayOfWeek day, LocalTime time) {
        List<String> freeRooms = new ArrayList<>();

        for (Course course : courses) {
            if (course.getBuilding().equals(building) && !isRoomOccupied(course, day, time)) {

            }
        }

        return freeRooms;
    }

    // TODO: testing needed
    private boolean isRoomOccupied(Course course, DayOfWeek day, LocalTime time) {
        String dayAbbreviation = TimeSlot.getDayStringFromEnum(day);

        for (TimeSlot slot : course.getTimeSlot()) {
            if (slot.getDays().contains(dayAbbreviation) &&
                    !time.isBefore(slot.getStartTime()) && !time.isAfter(slot.getEndTime())) {
                return true;
            }
        }
        return false;
    }

    // TODO: testing
    @Override
    public List<Course> getScheduleForRoom(String building, String room) {
        List<Course> schedule = new ArrayList<>();

        for (Course course : courses) {
            if (course.getBuilding().equals(building) && course.getRoom().equals(room)) {
                schedule.add(course);
            }
        }

        return schedule;
    }

    // TODO: testing
    @Override
    public List<Course> getCoursesInBuilding(String building) {
        List<Course> coursesInBuilding = new ArrayList<>();

        for (Course course : courses) {
            if (course.getBuilding().equals(building)) {
                coursesInBuilding.add(course);
            }
        }

        return coursesInBuilding;
    }


    @Override
    public List<Course> findCoursesByTimeSlot(DayOfWeek day, LocalTime startTime, LocalTime endTime) {
        List<Course> coursesAtTime = new ArrayList<>();

        for (Course course : courses) {
            for (TimeSlot slot : course.getTimeSlot()) {
                if (slot.getDays().contains(day.toString().substring(0, 2)) &&
                        startTime.equals(slot.getStartTime()) && endTime.equals(slot.getEndTime())) {
                    coursesAtTime.add(course);
                }
            }
        }

        return coursesAtTime;
    }

}
