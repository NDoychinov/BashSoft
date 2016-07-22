package bg.softuni.contracts;

import java.util.HashMap;

/**
 * Created by Niki on 13.7.2016 г..
 */
public interface DataFilter {
    void printFilteredStudents(
            HashMap<String, Double> studentsWithMarks,
            String filterType,
            Integer numberOfStudents);
}
