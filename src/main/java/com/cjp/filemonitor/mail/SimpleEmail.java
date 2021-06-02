package com.cjp.filemonitor.mail;


import com.cjp.filemonitor.filescanner.MainApp;

import javax.mail.Session;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import static com.cjp.filemonitor.filescanner.MainApp.*;


public class SimpleEmail {

    public static void main(String[] args) throws IOException, InterruptedException {

        boolean isOn = true;
        while (isOn){
            File repertoireVise = new File(getStringFromConfig("C:\\Users\\achampag\\IdeaProjects\\file-monitor\\src\\main\\java\\param.ini", "global","directory"));
            System.out.println("SimpleEmail Start");
            String smtpHostServer = "owa.clermont.unicancer.fr";
            String emailID = MainApp.getStringFromConfig("C:\\Users\\achampag\\IdeaProjects\\file-monitor\\src\\main\\java\\param.ini","email","to");


            Properties props = System.getProperties();

            props.put("mail.smtp.host", smtpHostServer);

            Session session = Session.getInstance(props, null);

            EmailUtil.sendEmail(session, emailID,"File Monitor : Récapitulatif ", "<html><b>Hello World</b>" + ScanRepertoire(repertoireVise) +"</html>");
            Thread.sleep(200000);
        }


    }

}
