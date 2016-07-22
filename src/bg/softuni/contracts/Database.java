package bg.softuni.contracts;

import java.io.IOException;

/**
 * Created by Niki on 13.7.2016 г..
 */
public interface Database extends Requester, FilteredTaker, OrderedTaker {

    void loadData(String fileName) throws IOException;

    void unloadData();
}
