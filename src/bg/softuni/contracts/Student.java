package bg.softuni.contracts;

import java.util.Map;

/**
 * Created by Niki on 13.7.2016 г..
 */
public interface Student {

    String getMarkForCourse(String courseName);

    void setMarkOnCourse(String courseName, int[] scores);

    void enrollInCourse(Course course);

    Map<String, Double> getMarksByCourseName();

    Map<String, Course> getEnrolledCourses();

    String getUserName();
}
