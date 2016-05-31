import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Niki on 30.5.2016 г..
 */
public class CommandInterpreter {
    public static void interpretCommand(String input) throws IOException {
        String[] data = input.split("\\s+");
        String command = data[0];

        switch (command) {
            case "open":
                tryOpenFile(input, data);
                break;
            case "mkdir":
                tryCreateDirectory(input, data);
                break;
            case "ls":

                break;

            case "cmp":

                break;

            case "changeDirRel":

                break;

            case "changeDirAbs":

                break;

            case "readDb":

                break;

            case "filter":

                break;

            case "order":

                break;

            case "download":

                break;

            case "downloadAsynch":

                break;

            case "help":

                break;
            default:
                displayInvalidCommandMessage(input);
                break;
        }

    }

    public static void displayInvalidCommandMessage(String input) {
        String output = String.format("The command '%s' is invalid", input);
        OutputWriter.writeMessageOnNewLine(output);
    }

    public static void tryOpenFile(String input, String[] data) throws IOException {
        if (data.length != 2) {
            displayInvalidCommandMessage(input);
            return;
        }

        String fileName = data[1];
        String filePath = SessionData.currentPath + "\\" + fileName;
        File file = new File(filePath);
        Desktop.getDesktop().open(file);
    }

    public static void tryCreateDirectory(String input, String[] data) {
        if (data.length == 2) {
            String folderName = data[1];
            IOManager.createDirectoryInCurrentFolder(folderName);
        }
    }

    private static void tryTraverseFolders(String input, String[] data){
        if (data.length != 1 && data.length != 2){
            displayInvalidCommandMessage(input);
            return;
        }
        if (data.length == 1){
            IOManager.traverseDirectory(0);
        }

        if (data.length == 2){
            IOManager.traverseDirectory(Integer.parseInt(data[1]));
        }
    }


}
