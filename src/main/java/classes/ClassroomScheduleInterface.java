package classes;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public interface ClassroomScheduleInterface {
    List<String> findFreeRooms(String building, DayOfWeek day, LocalTime time);
    List<Course> getScheduleForRoom(String building, String room);
    List<Course> getCoursesInBuilding(String building);
    List<Course> findCoursesByTimeSlot(DayOfWeek day, LocalTime startTime, LocalTime endTime);
}
