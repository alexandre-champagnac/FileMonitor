package com.cjp.filemonitor;

import com.cjp.filemonitor.config.AppConfig;
import com.cjp.filemonitor.config.ConfigLoader;
import com.cjp.filemonitor.inputs.GlobalMonitor;
import com.cjp.filemonitor.inputs.MonitoringReport;
import com.cjp.filemonitor.inputs.database.DatabaseMonitor;
import com.cjp.filemonitor.inputs.directory.DirectoryMonitor;
import com.cjp.filemonitor.inputs.samba.SambaMonitor;
import com.cjp.filemonitor.mail.EmailUtils;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        AppConfig appConfig = ConfigLoader.loadConfigFromIniFile(Path.of("C:\\Users\\achampag\\paramini\\config.test.ini"));

        List<GlobalMonitor> monitors = new ArrayList<>();
        monitors.add(new DirectoryMonitor(Path.of(appConfig.getMonitoringDirectory())));
        monitors.add(new DirectoryMonitor(Path.of("C:\\Users\\achampag\\.tmp\\folder2")));
        monitors.add(new DatabaseMonitor(appConfig));
        monitors.add(new SambaMonitor(appConfig));

        long deadline = (System.currentTimeMillis() - (appConfig.getMonitoringLifespan() * 60 * 1000));
        EmailUtils mailSender = new EmailUtils(appConfig.getEmailTo(), appConfig.getSmtpHostServer());
        mailSender.createSession();

        for (GlobalMonitor monitor : monitors) {
            monitor.initialize();
        }


        while (true){
            String forEmail = "<h1 style=\"color: #5e9ca0; text-align: center;\">CJP FileMonitor - R&eacute;sum&eacute;</h1>\n" +
                    "<h2 style=\"color: #2e6c80; text-align: center;\">R&eacute;capitulatif d'analyse</h2>";
            boolean isSendMail = false;
            for (GlobalMonitor monitor : monitors) {

                MonitoringReport report = monitor.analyse(deadline);
                if (report.hasProblem()) {
                    forEmail += report.toHTMLReport();
                    isSendMail = true;
                }
            }
            if (isSendMail) {
                mailSender.sendEmail("Recap FileMonitoring", "<ul>" + forEmail + "</ul>");
            }

            Thread.sleep(30 * 1000);
        }


        /*for (GlobalMonitor monitor : monitors) {
            monitor.close();
        }*/
    }

}
