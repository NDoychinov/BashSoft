package bg.softuni.contracts;

/**
 * Created by Niki on 13.7.2016 г..
 */
public interface Requester {

    void getStudentMarkInCourse(String courseName, String studentName);

    void getStudentsByCourse(String courseName);
}
