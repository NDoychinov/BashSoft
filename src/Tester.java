import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niki on 25.5.2016 г..
 */
public class Tester {
    public static void CompareContent(String actualOutput, String expectedOutput) {
        try {
            OutputWriter.writeMessageOnNewLine("Reading files...");
            String mismatchPath = GetMismatchPath(expectedOutput);

            List<String> actualOutputString = ReadTextFile(actualOutput);
            List<String> expectedOutputString = ReadTextFile(expectedOutput);

            boolean mismatch = CompareStrings(actualOutputString, expectedOutputString, mismatchPath);

            if (mismatch) {
                List<String> mismatchString = ReadTextFile(mismatchPath);
                mismatchString.forEach(OutputWriter::writeMessageOnNewLine);
            } else {
                OutputWriter.writeMessageOnNewLine("Files are identical. There are no mismatches.");
            }
        } catch (IOException e) {
            OutputWriter.displayException(ExceptionMessages.INVALID_PATH);
        }
    }

    public static String GetMismatchPath(String expectedOutput) {
        int index = expectedOutput.lastIndexOf('\\');
        String directoryPath = expectedOutput.substring(0, index);
        return directoryPath + "\\mismatch.txt";
    }

    private static List<String> ReadTextFile(String filePath) throws IOException {
        List<String> text = new ArrayList<>();

        File file = new File(filePath);

        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);

        String line = reader.readLine();

        while (line != null) {
            text.add(line);
            line = reader.readLine();
        }

        reader.close();

        OutputWriter.writeMessageOnNewLine("File not found");

        return text;
    }

    private static boolean CompareStrings(
            List<String> actualOutputString,
            List<String> expectedOutputString,
            String mismatchPath) {

        OutputWriter.writeMessageOnNewLine("Comparing files...");
        String output;
        boolean isMismatch = false;

        try {
            FileWriter fileWriter = new FileWriter(mismatchPath);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            int maxLength = Math.max(actualOutputString.size(), expectedOutputString.size());

            for (int i = 0; i < maxLength; i++) {
                String actualLine = actualOutputString.get(i);
                String expectedLine = expectedOutputString.get(i);

                if (!actualLine.equals(expectedLine)) {
                    output = String.format("mismatch -> expected{%s}, actual{%s}%n", expectedLine, actualLine);
                    isMismatch = true;
                } else {
                    output = String.format("line match -> %s%n", actualLine);
                }
                writer.write(output);
            }

            writer.close();
        } catch (IOException e) {
            isMismatch = true;
            OutputWriter.displayException(ExceptionMessages.CANNOT_ACCESS_FILE);
        } catch (IndexOutOfBoundsException e) {
            isMismatch = true;
            OutputWriter.displayException(ExceptionMessages.INVALID_OUTPUT_LENGTH);
        }

        return isMismatch;
    }

}
