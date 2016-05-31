import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Niki on 18.5.2016 г..
 */
public class Data {
    public boolean isDataInitialized = false;
    public HashMap<String, HashMap<String, ArrayList<Integer>>> studentsByCourse;

    public void initializeData() {
        if (!isDataInitialized) {
            studentsByCourse = new HashMap<>();
            readData();
            isDataInitialized = true;
        }
    }

    public void readData() {
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
    }

    public List<Integer> getStudentMarksInCourse(String course, String student) {
        if (studentsByCourse.containsKey(course) && studentsByCourse.get(course).containsKey(student)) {
            return studentsByCourse.get(course).get(student);
        }

        return null;
    }

    public HashMap<String, ArrayList<Integer>> getStudentsByCourse(String course) {
        if (studentsByCourse.containsKey(course)) {
            return studentsByCourse.get(course);
        }

        return null;
    }

}
