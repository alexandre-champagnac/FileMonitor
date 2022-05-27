package com.cjp.filemonitor;

import com.cjp.filemonitor.config.AppConfig;
import com.cjp.filemonitor.config.ConfigLoader;
import com.cjp.filemonitor.inputs.GlobalMonitor;
import com.cjp.filemonitor.inputs.MonitoringReport;
import com.cjp.filemonitor.inputs.directory.DirectoryMonitor;



import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("first parameter must be the absolute path to the .ini config file");
            return;
        }
        String configPath = args[0];
        AppConfig appConfig = ConfigLoader.loadConfigFromIniFile(Path.of(configPath));

        List<GlobalMonitor> monitors = new ArrayList<>();
        monitors.add(new DirectoryMonitor(Path.of(appConfig.getMonitoringDirectory())));

        for (GlobalMonitor monitor : monitors) {
            monitor.initialize();
        }


        while (true){
            System.out.println("Scan ... ");
            long deadline = (System.currentTimeMillis() - (appConfig.getMonitoringLifespan() * 60 * 1000));
            String forEmail = "CJP File Monitor récapitulatif d'analyse : \n";
            boolean isSendMail = false;
            for (GlobalMonitor monitor : monitors) {

                MonitoringReport report = monitor.analyse(deadline);
                if (report.hasProblem()) {
                    forEmail += report.toHTMLReport();
                    isSendMail = true;
                }
            }
            if (isSendMail) {
                System.out.println("Recap FileMonitoring " +  forEmail );
            }

            Thread.sleep(appConfig.getMonitoringLifespan() * 60 * 1000 );
        }


        /*for (GlobalMonitor monitor : monitors) {
            monitor.close();
        }*/
    }

}
