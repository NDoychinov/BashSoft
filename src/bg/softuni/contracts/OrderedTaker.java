package bg.softuni.contracts;

/**
 * Created by Niki on 13.7.2016 г..
 */
public interface OrderedTaker {

    void orderAndTake(String courseName, String orderType, int studentsToTake);

    void orderAndTake(String courseName, String orderType);

}
