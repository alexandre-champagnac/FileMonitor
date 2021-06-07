package com.cjp.filemonitor.config;


public class AppConfig {
    private String monitoringDirectory;
    private long monitoringLifespan;
    private String emailTo;
    private String smtpHostServer;

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

    public String getEmailTo() {
        return emailTo;
    }

    void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getSmtpHostServer() {
        return smtpHostServer;
    }

    public void setSmtpHostServer(String smtpHostServer) {
        this.smtpHostServer = smtpHostServer;
    }
}
