package bg.softuni.contracts;

import java.io.IOException;

/**
 * Created by Niki on 13.7.2016 г..
 */
public interface Interpreter {
    void interpretCommand(String input) throws IOException;
}
