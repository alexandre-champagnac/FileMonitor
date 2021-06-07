package com.cjp.filemonitor.inputs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DirectoryMonitor {

    private Path directory;

    public Path getDirectory() {
        return directory;
    }

    public DirectoryMonitor(Path directory) {
        this.directory = directory;
    }

    /**
     * Read a directory and return all the files older than filesOlderThanTimestamp
     *
     * @param filesOlderThanTimestamp number of xxxxxxx for a file to be consider old
     * @returns
     */
    public  List<File> analyseDirectory(long filesOlderThanTimestamp) throws IOException {
        File targetFile = new File(directory.toString());

        File[] folder = targetFile.listFiles();
        if (folder == null){
            throw new FileNotFoundException("Le repertoire n'existe pas " + targetFile);
        }

        File oldestFile = null;
        ArrayList<File> fileList = new ArrayList<File>();

        for (File currentFile : folder) {

            if (isTimestampLowerThan(currentFile.lastModified() , filesOlderThanTimestamp)){
                if(oldestFile == null){
                    oldestFile = currentFile;
                }
                if(isTimestampLowerThan(currentFile.lastModified() , oldestFile.lastModified())){
                    oldestFile = currentFile;
                }

                fileList.add(currentFile);
            }

        }

        return fileList;
    }




    boolean isTimestampLowerThan(long var1, long var2){
        boolean isLower = true;
        if (var1 > var2 || var1 == var2){
            isLower = false;
        }

        return isLower;
    }

}
