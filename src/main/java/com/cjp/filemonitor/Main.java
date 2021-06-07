package com.cjp.filemonitor;

import com.cjp.filemonitor.config.AppConfig;
import com.cjp.filemonitor.config.ConfigLoader;
import com.cjp.filemonitor.inputs.DirectoryMonitor;
import com.cjp.filemonitor.mail.EmailUtils;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, MessagingException {


        AppConfig appConfig = ConfigLoader.loadConfigFromIniFile(Path.of("C:\\Users\\achampag\\IdeaProjects\\FileMonitor\\src\\main\\java\\param.ini"));


        DirectoryMonitor monitor = new DirectoryMonitor(Path.of(appConfig.getMonitoringDirectory()));


        long deadline = (System.currentTimeMillis() - (appConfig.getMonitoringLifespan()* 60 * 1000));
        List<File> listOlfFiles = monitor.analyseDirectory(deadline);


        System.out.println("Starting preparing email");

        EmailUtils mailSender = new EmailUtils();

        mailSender.sendEmail("FileMonitorCjp","Il y a " + listOlfFiles.size() + "vieux fichiers.",appConfig.getEmailTo());




    }

}
