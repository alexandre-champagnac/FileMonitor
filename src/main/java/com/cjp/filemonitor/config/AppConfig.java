package com.cjp.filemonitor.config;


public class AppConfig {
    private String monitoringDirectory;
    private long monitoringLifespan;


    public String getMonitoringDirectory() {
        return monitoringDirectory;
    }

    void setMonitoringDirectory(String monitoringDirectory) {
        this.monitoringDirectory = monitoringDirectory;
    }

    public long getMonitoringLifespan() {
        return monitoringLifespan;
    }

    void setMonitoringLifespan(long monitoringLifespan) {
        this.monitoringLifespan = monitoringLifespan;
    }

}



