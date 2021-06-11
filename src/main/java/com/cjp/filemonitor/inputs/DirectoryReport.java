package com.cjp.filemonitor.inputs;

import java.nio.file.Path;

public class DirectoryReport implements MonitoringReport {

    private Path path;
    private long cptOldFiles;
    private boolean isOldFilesPresents;

    public DirectoryReport(Path path, long cptOldFiles, boolean isOldFilesPresents) {
        this.path = path;
        this.cptOldFiles = cptOldFiles;
        this.isOldFilesPresents = isOldFilesPresents;
    }



    long getCptOldFiles() {
        return cptOldFiles;
    }

    @Override
    public String toHTMLReport() {
        return "<li style=\"text-align: center;\">Directory Report : Dans le dossier correspondant à <b>" + path + "</b> nous avons trouver <b>" + cptOldFiles +"</b> fichiers trop vieux</li>\n";
    }

    @Override
    public boolean hasProblem() {
        return isOldFilesPresents;
    }


}
