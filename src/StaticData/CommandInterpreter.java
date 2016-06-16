package StaticData;

import IO.IOManager;
import IO.OutputWriter;
import Judge.Tester;
import Repository.StudentsRepository;
import DownloadManager.DowloadManager;
import Repository.RepositorySorters;
import com.sun.corba.se.spi.activation.Repository;

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
                tryTraverseFolders(input, data);
                break;

            case "cmp":
                tryCompareFiles(input, data);
                break;

            case "changeDirRel":
                tryChangeRelativePath(input, data);
                break;

            case "changeDirAbs":
                tryChangeAbsolutePath(input, data);
                break;

            case "readDb":
                tryReadDatabaseFromFile(input, data);
                break;

            case "filter":
                tryPrintFilteredStudents(input, data);
                break;

            case "order":
                tryPrintOrderedStudents(input, data);
                break;

            case "sort":
                tryPrintSortedStudents(input, data);
                break;

            case "download":
                tryDownloadFile(input, data);
                break;

            case "downloadAsynch":
                tryDownloadFileOnNewThread(input, data);
                break;

            case "help":
                getHelp();
                break;

            case "show":
                tryShowWantedCourse(input, data);
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

    private static void tryTraverseFolders(String input, String[] data) {
        if (data.length != 1 && data.length != 2) {
            displayInvalidCommandMessage(input);
            return;
        }
        if (data.length == 1) {
            IOManager.traverseDirectory(0);
        }

        if (data.length == 2) {
            IOManager.traverseDirectory(Integer.parseInt(data[1]));
        }
    }

    public static void tryCompareFiles(String input, String[] data) {
        if (data.length != 3) {
            displayInvalidCommandMessage(input);
            return;
        }

        String firstPath = data[1];
        String secondPath = data[2];
        Tester.compareContent(firstPath, secondPath);
    }

    public static void tryChangeRelativePath(String input, String[] data) {
        if (data.length != 2) {
            displayInvalidCommandMessage(input);
            return;
        }

        String relativePath = data[1];
        IOManager.changeCurrentDirRelativePath(relativePath);
    }

    public static void tryChangeAbsolutePath(String input, String[] data) {
        if (data.length != 2) {
            displayInvalidCommandMessage(input);
            return;
        }

        String absolutePath = data[1];
        IOManager.changeCurrentDirAbsolute(absolutePath);
    }

    public static void tryReadDatabaseFromFile(String input, String[] data) throws IOException {
        if (data.length != 2) {
            displayInvalidCommandMessage(input);
            return;
        }

        String fileName = data[1];
        StudentsRepository.initializeData(fileName);
    }

    public static void getHelp() {
        OutputWriter.writeMessageOnNewLine("mkdir path - make directory");
        OutputWriter.writeMessageOnNewLine("ls depth - traverse directory");
        OutputWriter.writeMessageOnNewLine("cmp path1 path2 - compare two files");
        OutputWriter.writeMessageOnNewLine("changeDirRel relativePath - change directory");
        OutputWriter.writeMessageOnNewLine("changeDir absolutePath - change directory");
        OutputWriter.writeMessageOnNewLine("readDb path - read students data base");
        OutputWriter.writeMessageOnNewLine("filterExcelent - filter excelent students (the output is written on the console)");
        OutputWriter.writeMessageOnNewLine("filterExcelent path - filter excelent students (the output is written in a given path)");
        OutputWriter.writeMessageOnNewLine("filterAverage - filter average students (the output is written on the console)");
        OutputWriter.writeMessageOnNewLine("filterAverage path - filter average students (the output is written in a file)");
        OutputWriter.writeMessageOnNewLine("filterPoor - filter low grade students (the output is on the console)");
        OutputWriter.writeMessageOnNewLine("filterPoor path - filter low grade students (the output is written in a file)");
        OutputWriter.writeMessageOnNewLine("order - sort students in increasing order (the output is written on the console)");
        OutputWriter.writeMessageOnNewLine("order path - sort students in increasing order (the output is written in a given path)");
        OutputWriter.writeMessageOnNewLine("decOrder - sort students in decreasing order (the output is written on the console)");
        OutputWriter.writeMessageOnNewLine("decOrder path - sort students in decreasing order (the output is written in a given path)");
        OutputWriter.writeMessageOnNewLine("download pathOfFile - download file (saved in current directory)");
        OutputWriter.writeMessageOnNewLine("downloadAsync path - download file asynchronously (save in the current directory)");
        OutputWriter.writeMessageOnNewLine("help - get help");
        OutputWriter.writeEmptyLine();
    }

    public static void tryDownloadFile(String input, String[] data) {
        if (data.length != 2) {
            displayInvalidCommandMessage(input);
            return;
        }

        String fileUrl = data[1];
        DowloadManager.download(fileUrl);
    }

    public static void tryDownloadFileOnNewThread(String input, String[] data) {
        if (data.length != 2) {
            displayInvalidCommandMessage(input);
            return;
        }

        String fileUrl = data[1];
        DowloadManager.downloadOnNewThread(fileUrl);
    }

    public static void tryShowWantedCourse(String input, String[] data) {
        if (data.length != 2 && data.length != 3) {
            displayInvalidCommandMessage(input);
        }

        if (data.length == 2) {
            String courseName = data[1];
            StudentsRepository.getStudentsByCourse(courseName);
        }

        if (data.length == 3) {
            String courseName = data[1];
            String userName = data[2];
            StudentsRepository.getStudentMarksInCourse(courseName, userName);
        }
    }

    public static void tryPrintFilteredStudents(String input, String[] data) {
        if (data.length != 3 && data.length != 4) {
            displayInvalidCommandMessage(input);
        }

        String course = data[1];
        String filter = data[2];

        if (data.length == 3) {
            StudentsRepository.printFilteredStudents(course, filter, null);
            return;
        }

        Integer numberOfStudents = Integer.valueOf(data[3]);

        if (data.length == 4) {
            StudentsRepository.printFilteredStudents(course, filter, numberOfStudents);
        }
    }

    public static void tryPrintOrderedStudents(String input, String[] data) {
        if (data.length != 3 && data.length != 4) {
            displayInvalidCommandMessage(input);
        }

        String course = data[1];
        String filter = data[2];

        if (data.length == 3) {
            StudentsRepository.printOrderedStudents(course, filter, null);
            return;
        }

        Integer numberOfStudents = Integer.valueOf(data[3]);

        if (data.length == 4) {
            StudentsRepository.printOrderedStudents(course, filter, numberOfStudents);
        }
    }

    public static void tryPrintSortedStudents(String input, String[] data){
        if (data.length != 3 && data.length != 4) {
            displayInvalidCommandMessage(input);
        }

        //TO DO
    }
}
