package com.cjp.filemonitor.inputs.database;

import com.cjp.filemonitor.inputs.MonitoringReport;

import java.util.Date;

public class DataBaseReport implements MonitoringReport {

    private final Date lastModified;
    private final String dbname;
    private final boolean isOldentriesPresent;


    public DataBaseReport(Date sinceHere, String dbname, boolean isOldentriesPresents) {
        this.lastModified = sinceHere;
        this.dbname = dbname;
        this.isOldentriesPresent = isOldentriesPresents;
    }


    @Override
    public String toHTMLReport() {
        return "Database Report : <b>" + dbname + "</b> les fichiers ne partent pas depuis : <b>" + lastModified + "</b>";
    }

    @Override
    public boolean hasProblem() {
        return isOldentriesPresent;
    }
}
