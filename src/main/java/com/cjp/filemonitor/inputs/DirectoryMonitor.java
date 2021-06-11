package com.cjp.filemonitor.inputs;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;

public class DirectoryMonitor implements GlobalMonitor {

    private Path directory;

    private boolean isOldFilepresent = false;

    public Path getDirectory() {
        return directory;
    }

    public DirectoryMonitor(Path directory) {
        this.directory = directory;
    }


    boolean isTimestampLowerThan(long var1, long var2){
        boolean isLower = true;
        if (var1 > var2 || var1 == var2){
            isLower = false;
        }

        return isLower;
    }


    @Override
    public void initialize() throws Exception {

    }

    @Override
    public MonitoringReport analyse(long deadline) throws Exception {
        File targetFile = directory.toFile();

        File[] folder = targetFile.listFiles();
        if (folder == null){
            throw new FileNotFoundException("Le repertoire n'existe pas " + targetFile);
        }

        File oldestFile = null;
        ArrayList<File> oldFileList = new ArrayList<File>();

        for (File currentFile : folder) {

            if (isTimestampLowerThan(currentFile.lastModified() , deadline)){
                if(oldestFile == null){
                    oldestFile = currentFile;
                    isOldFilepresent = true;
                }
                if(isTimestampLowerThan(currentFile.lastModified() , oldestFile.lastModified())){
                    oldestFile = currentFile;
                }

                oldFileList.add(currentFile);
            }

        }

        DirectoryReport report = new DirectoryReport(directory, oldFileList.size(),isOldFilepresent);

        return  report;
    }

    @Override
    public void close() throws Exception {

    }


}
