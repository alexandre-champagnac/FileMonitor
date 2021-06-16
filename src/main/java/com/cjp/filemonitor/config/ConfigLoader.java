package com.cjp.filemonitor.config;

import org.ini4j.Ini;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class ConfigLoader {

    /**
     * Reads an ini file and parse it to provide a configuration
     *
     * @param iniPath path of the ini file
     * @return an AppConfig
     * @throws IOException if failed to read the ini file
     */
    public static AppConfig loadConfigFromIniFile(Path iniPath) throws IOException {

        Ini inifile = new Ini(new File(String.valueOf(iniPath)));

        String directory = inifile.get("global", "directory");
        String email = inifile.get("email", "to");
        String intervalString = inifile.get("global", "intervalInMinute");
        String smtpHostServer = inifile.get("global","smtpHostServer");
        String urlDb = inifile.get("bdd","url");
        String pseudoDb = inifile.get("bdd","pseudo");
        String passwordDb= inifile.get("bdd","password");
        String smbUser = inifile.get("samba","smbUsername");
        String smbPassword = inifile.get("samba","smbPassword");
        String smbPath = inifile.get("samba","smbPath");
        String smbhostName = inifile.get("samba","smbHostName");
        String smbShareName = inifile.get("samba","smbShareName");
        String smbSearchpattern = inifile.get("samba","smbSearchPattern");


        long interval = Long.parseLong(intervalString);

        AppConfig conf = new AppConfig();
        conf.setEmailTo(email);
        conf.setMonitoringDirectory(directory);
        conf.setMonitoringLifespan(interval);
        conf.setSmtpHostServer(smtpHostServer);
        conf.setUrlDb(urlDb);
        conf.setPseudoDb(pseudoDb);
        conf.setPassword(passwordDb);
        conf.setSambausername(smbUser);
        conf.setSambapassword(smbPassword);
        conf.setSambaPath(smbPath);
        conf.setSmbhostName(smbhostName);
        conf.setShareName(smbShareName);
        conf.setSearchPattern(smbSearchpattern);


        return conf;
    }


}
