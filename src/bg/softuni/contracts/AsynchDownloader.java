package bg.softuni.contracts;

/**
 * Created by Niki on 13.7.2016 г..
 */
public interface AsynchDownloader extends Downloader {

    void downloadOnNewThread(String fileUrl);
}
