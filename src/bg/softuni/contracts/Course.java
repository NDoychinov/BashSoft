package bg.softuni.contracts;

import java.util.Map;

/**
 * Created by Niki on 13.7.2016 г..
 */
public interface Course extends Comparable<Course> {

    String getName();

    Map<String, Student> getStudentsByName();

    void enrollStudent(Student student);
}
