import java.util.*;

/**
 * Created by Niki on 20.5.2016 г..
 */
public class StudentsRepository {
    public static boolean isDataInitilized = false;
    public static HashMap<String, HashMap<String, ArrayList<Integer>>> studentsByCourse;

    public static void InitializeData() {
        if (isDataInitilized) {
            System.out.println(ExceptionMessages.DATA_ALREADY_INITIALIZED);
        }
        studentsByCourse = new HashMap<>();
        readData();
    }

    public static void readData() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("")) {
            String[] tokens = input.split("\\s+");
            String course = tokens[0];
            String student = tokens[1];
            Integer mark = Integer.parseInt(tokens[2]);

            if (!studentsByCourse.containsKey(course)) {
                studentsByCourse.put(course, new HashMap<>());
                studentsByCourse.get(course).put(student, new ArrayList<>());
                studentsByCourse.get(course).get(student).add(mark);
            } else if (!studentsByCourse.get(course).containsKey(student)) {
                studentsByCourse.get(course).put(student, new ArrayList<>());
                studentsByCourse.get(course).get(student).add(mark);
            } else {
                studentsByCourse.get(course).get(student).add(mark);
            }

            input = sc.nextLine();
        }
        isDataInitilized = true;
        OutputWriter.writeMessageOnNewLine("Data read.");
    }

    private static boolean IsQueryForCoursePossible(String courseName) {
        if (!isDataInitilized) {
            OutputWriter.displayException(ExceptionMessages.DATA_NOT_INITIALIZED);
            return false;
        }
        if (!studentsByCourse.containsKey(courseName)) {
            OutputWriter.displayException(ExceptionMessages.NON_EXISTING_COURSE);
            return false;
        }

        return true;
    }

    private static boolean IsQueryForStudentPossiblе(String courseName, String studentName) {
        if (!IsQueryForCoursePossible(courseName)) {
            return false;
        }

        if (!studentsByCourse.get(courseName).containsKey(studentName)) {
            OutputWriter.displayException(ExceptionMessages.NON_EXISTING_STUDENT);
            return false;
        }

        return true;
    }

    public static void GetStudentMarksInCourse(String course, String student) {
        if (!IsQueryForStudentPossiblе(course, student)) {
            return;
        }

        ArrayList<Integer> marks = studentsByCourse.get(course).get(student);
        OutputWriter.printStudent(student, marks);
    }

    public static void GetStudentsByCourse(String course) {
        if (!IsQueryForCoursePossible(course)) {
            return;
        }

        OutputWriter.writeMessageOnNewLine(course + ":");

        for (Map.Entry<String,ArrayList<Integer>> student : studentsByCourse.get(course).entrySet()) {
            OutputWriter.printStudent(student.getKey(), student.getValue());
        }
    }

}
