package com.cjp.filemonitor.inputs;

import java.nio.file.Path;

public class DirectoryReport implements MonitoringReport {

    private Path path;
    private long cptOldFiles;


    public DirectoryReport(Path path, long cptOldFiles) {
        this.path = path;
        this.cptOldFiles = cptOldFiles;
    }

    long getCptOldFiles() {
        return cptOldFiles;
    }

    @Override
    public String toHTMLReport() {
        return "Dans le dossier correspondant à :" + path + "Nous avons trouver " + cptOldFiles + "fichiers trop vieux";
    }







}
