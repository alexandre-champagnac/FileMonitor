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
        long interval = Long.parseLong(intervalString);

        AppConfig conf = new AppConfig();
        conf.setEmailTo(email);
        conf.setMonitoringDirectory(directory);
        conf.setMonitoringLifespan(interval);


        return conf;
    }


}
