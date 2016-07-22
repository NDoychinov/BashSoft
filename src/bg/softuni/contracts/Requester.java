package bg.softuni.contracts;

import bg.softuni.dataStructures.SimpleSortedList;

import java.util.Comparator;

/**
 * Created by Niki on 13.7.2016 г..
 */
public interface Requester {

    void getStudentMarkInCourse(String courseName, String studentName);

    void getStudentsByCourse(String courseName);

    SimpleSortedList<Course> getAllCoursesSorted(Comparator<Course> cmp);

    SimpleSortedList<Student> getAllStudentsSorted(Comparator<Student> cmp);

}
