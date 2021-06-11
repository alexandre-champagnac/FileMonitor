package com.cjp.filemonitor.config;


public class AppConfig {
    private String monitoringDirectory;
    private long monitoringLifespan;
    private String emailTo;
    private String smtpHostServer;
    private String urlDb;
    private String pseudoDb;
    private String password;


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

    public String getUrlDb() {
        return urlDb;
    }

    public void setUrlDb(String urlDb) {
        this.urlDb = urlDb;
    }

    public String getPseudoDb() {
        return pseudoDb;
    }

    public void setPseudoDb(String pseudoDb) {
        this.pseudoDb = pseudoDb;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
