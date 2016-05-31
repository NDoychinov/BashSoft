import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Niki on 30.5.2016 г..
 */
public class InputReader {
    private static final String endCommand = "quit";

    public static void readCommands() throws IOException {
        OutputWriter.writeMessageOnNewLine(String.format("%s", SessionData.currentPath));
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().trim();

        while (!input.equals(endCommand)){
            CommandInterpreter.interpretCommand(input);
            OutputWriter.writeMessage(String.format("%s", SessionData.currentPath));

            input = sc.nextLine().trim();
        }
    }

}
