package com.cjp.filemonitor.config;


import com.cjp.filemonitor.inputs.samba.SambaReport;

public class AppConfig {
    private String monitoringDirectory;
    private long monitoringLifespan;
    private String emailTo;
    private String smtpHostServer;
    private String urlDb;
    private String pseudoDb;
    private String password;
    private String sambausername;
    private String sambapassword;
    private String SambaPath;
    private String smbhostName;
    private String shareName;
    private String searchPattern;

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

    public String getSambausername() {
        return sambausername;
    }

    public void setSambausername(String sambausername) {
        this.sambausername = sambausername;
    }

    public String getSambapassword() {
        return sambapassword;
    }

    public void setSambapassword(String sambapassword) {
        this.sambapassword = sambapassword;
    }

    public String getSambaPath() {
        return SambaPath;
    }

    public void setSambaPath(String sambaPath) {
        SambaPath = sambaPath;
    }

    public String getSmbhostName() {
        return smbhostName;
    }

    public void setSmbhostName(String smbhostName) {
        this.smbhostName = smbhostName;
    }

    public String getShareName() {
        return shareName;
    }

    public void setShareName(String shareName) {
        this.shareName = shareName;
    }

    public String getSearchPattern() {
        return searchPattern;
    }

    public void setSearchPattern(String searchPattern) {
        this.searchPattern = searchPattern;
    }
}
