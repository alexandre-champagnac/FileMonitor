package com.cjp.filemonitor.inputs;
import java.util.Date;

public class DataBaseReport implements MonitoringReport{

    //private String databaseName;
    private Date lastModified;
    private String dbname;
    private boolean isOldentriesPresent;


    public DataBaseReport(Date sinceHere, String dbname, boolean isOldentriesPresents) {
        this.lastModified = sinceHere;
        this.dbname = dbname;
        this.isOldentriesPresent = isOldentriesPresents;
    }


    @Override
    public String toHTMLReport() {


        if (!isOldentriesPresent){
            return "Il n'y à pas de soucis";
        }else{
            return "Dans la bdd correspondant à :" + dbname + " les fichiers ne partent pas depuis : " + lastModified + "";
        }


    }
}
