package com.server.app.utils;

import java.io.File;

public class Utils {

    public static boolean checkFileExist(String filePath, String fileName) {
        File dir = new File(filePath);
        File[] dirContents = dir.listFiles();

        for (int i = 0; i < dirContents.length; i++) {
            if (dirContents[i].getName().equals(fileName)) {
                return true;
            }
        }
        return false;
    }
}
