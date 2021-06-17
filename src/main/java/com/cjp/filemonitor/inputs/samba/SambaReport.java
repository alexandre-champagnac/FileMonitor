package com.cjp.filemonitor.inputs.samba;

import com.cjp.filemonitor.inputs.MonitoringReport;

public class SambaReport implements MonitoringReport {

    private final String sambaPath;
    private final long cptOldFiles;
    private final boolean isOldFilesPresents;

    public SambaReport(String sambaPath, long cptOldFiles, boolean isOldFilesPresents) {
        this.sambaPath = sambaPath;
        this.cptOldFiles = cptOldFiles;
        this.isOldFilesPresents = isOldFilesPresents;
    }




    @Override
    public String toHTMLReport() {
        return "<li style=\"text-align: center;\">Samba Report : Dans le dossier correspondant à <b>" + sambaPath + "</b> nous avons trouver <b>" + cptOldFiles + "</b> fichiers trop vieux</li>\n";
    }

    @Override
    public boolean hasProblem() {
        boolean problem = true;




        if(isOldFilesPresents){
            problem = true;
        }

        return problem;
    }
}
