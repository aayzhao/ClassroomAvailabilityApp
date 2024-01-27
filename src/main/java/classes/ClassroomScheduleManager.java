package classes;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public class ClassroomScheduleManager implements ClassroomScheduleInterface {
    private List<Course> courses;

    public ClassroomScheduleManager(List<Course> courses) {
        this.courses = courses;
    }


    @Override
    public List<String> findFreeRooms(String building, DayOfWeek day, LocalTime time) {
        return null;
    }

    @Override
    public List<Course> getScheduleForRoom(String building, String room) {
        return null;
    }

    @Override
    public List<Course> getCoursesInBuilding(String building) {
        return null;
    }

    @Override
    public List<Course> findCoursesByTimeSlot(DayOfWeek day, LocalTime startTime, LocalTime endTime) {
        return null;
    }
}
