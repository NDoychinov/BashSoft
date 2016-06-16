package DownloadManager;

import IO.OutputWriter;
import StaticData.ExceptionMessages;
import StaticData.SessionData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * Created by Niki on 16.6.2016 г..
 */
public class DowloadManager {
    private static Thread thread;

    public static void download(String fileUrl) {
        URL url = null;
        ReadableByteChannel rbc = null;
        FileOutputStream fos = null;

        try {
            if (Thread.currentThread().getName().equals("main")) {
                OutputWriter.writeMessageOnNewLine("Started downloading...");
            } else {
                OutputWriter.writeMessageOnNewLine(
                        String.format("Worker thread %d started download..", thread.getId()));
            }

            url = new URL(fileUrl);
            rbc = Channels.newChannel(url.openStream());
            String fileName = extractNameOfFile(url.toString());
            File file = new File(SessionData.currentPath + "/" + fileName);
            fos = new FileOutputStream(file);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

            if (Thread.currentThread().getName().equals("main")) {
                OutputWriter.writeMessageOnNewLine("Downloading complete...");
            } else {
                OutputWriter.writeMessageOnNewLine(
                        String.format("Worker thread %d complete downloading..", thread.getId()));
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (rbc != null) {
                    rbc.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void downloadOnNewThread(String fileUrl) {
        thread = new Thread(() -> download(fileUrl));
        thread.setDaemon(false);
        thread.start();
    }

    private static String extractNameOfFile(String fileUrl) {
        int indexOfLastSlash = fileUrl.lastIndexOf('/');

        if (indexOfLastSlash == -1) {
            try {
                throw new MalformedURLException(ExceptionMessages.INVALID_PATH);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return fileUrl.substring(indexOfLastSlash + 1);
    }
}
