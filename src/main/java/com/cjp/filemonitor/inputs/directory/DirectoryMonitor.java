package com.cjp.filemonitor.inputs.directory;

import com.cjp.filemonitor.inputs.GlobalMonitor;
import com.cjp.filemonitor.inputs.MonitoringReport;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;

public class DirectoryMonitor extends GlobalMonitor {

    private final Path directory;

    private boolean isOldFilepresent = false;


    public DirectoryMonitor(Path directory) {
        this.directory = directory;
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
