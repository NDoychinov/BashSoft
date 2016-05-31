import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Niki on 18.5.2016 г..
 */
public class Main {
    public static void main(String[] args) {
        IOManager.changeCurrentDirAbsolute("C:");
        System.out.println(SessionData.currentPath);

        IOManager.changeCurrentDirRelativePath("..");
        System.out.println(SessionData.currentPath);
    }
}
