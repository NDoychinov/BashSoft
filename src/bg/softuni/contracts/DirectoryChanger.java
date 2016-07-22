package bg.softuni.contracts;

/**
 * Created by Niki on 13.7.2016 г..
 */
public interface DirectoryChanger {

    void changeCurrentDirRelativePath(String relativePath);

    void changeCurrentDirAbsolute(String absolutePath);
}
