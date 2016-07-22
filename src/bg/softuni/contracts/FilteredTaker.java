package bg.softuni.contracts;

/**
 * Created by Niki on 13.7.2016 г..
 */
public interface FilteredTaker {

    void filterAndTake(String courseName, String filter, int studentsToTake);

    void filterAndTake(String courseName, String filter);
}
