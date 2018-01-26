package com.scottehboeh.glc.utils;

import java.io.File;

/**
 * Created by 1503257 on 08/12/2017.
 * <p>
 * File Utils
 * <p>
 * This class contains various useful methods for managing and handling
 * file-based objects.
 */
public class FileUtils {

    /**
     * List Files for Folder - Print out a list of Files within a Directory
     *
     * @param folder - Given File (Directory) to list files of
     */
    public void listFilesForFolder(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                System.out.println(fileEntry.getName());
            }
        }
    }

}
