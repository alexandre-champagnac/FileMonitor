package com.cjp.filemonitor.config;


public class AppConfig {
    private String monitoringDirectory;
    private long monitoringInterval;
    private String emailTo;

    public String getMonitoringDirectory() {
        return monitoringDirectory;
    }

    void setMonitoringDirectory(String monitoringDirectory) {
        this.monitoringDirectory = monitoringDirectory;
    }

    public long getMonitoringInterval() {
        return monitoringInterval;
    }

    void setMonitoringInterval(long monitoringInterval) {
        this.monitoringInterval = monitoringInterval;
    }

    public String getEmailTo() {
        return emailTo;
    }

    void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

}
