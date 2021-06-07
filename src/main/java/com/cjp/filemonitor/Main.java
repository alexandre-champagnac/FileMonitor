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
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, MessagingException {
        AppConfig appConfig = ConfigLoader.loadConfigFromIniFile(Path.of("C:\\Users\\achampag\\IdeaProjects\\FileMonitor\\src\\main\\java\\param.ini"));

        List<DirectoryMonitor> monitors = new ArrayList<>();
        monitors.add(new DirectoryMonitor(Path.of(appConfig.getMonitoringDirectory())));
        monitors.add(new DirectoryMonitor(Path.of("C:\\Users\\achampag\\.tmp\\folder2")));
        long deadline = (System.currentTimeMillis() - (appConfig.getMonitoringLifespan()* 60 * 1000));
        int cpt = 1;
        EmailUtils mailSender = new EmailUtils(appConfig.getEmailTo(),  appConfig.getSmtpHostServer());
        mailSender.createSession();


        for (DirectoryMonitor dir : monitors){
            System.out.println("traitement du dossier : " + cpt );
            List<File> listOldFiles = dir.analyseDirectory(deadline);
            mailSender.sendEmail("FileMonitorCjp","Il y a " + listOldFiles.size() + "vieux fichiers.");
            cpt++;
        }









    }

}
