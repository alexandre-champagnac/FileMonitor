package com.cjp.filemonitor.mail;


import com.cjp.filemonitor.filescanner.MainApp;

import javax.mail.Session;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import static com.cjp.filemonitor.filescanner.MainApp.ScanRepertoire;


public class SimpleEmail {

    public static void main(String[] args) throws IOException {
        File repertoireVise = new File("C:\\Users\\achampag\\IdeaProjects\\file-monitor\\src\\main\\java\\Sara");
        System.out.println("SimpleEmail Start");
        String smtpHostServer = "owa.clermont.unicancer.fr";
        String emailID = "Alexandre.CHAMPAGNAC@clermont.unicancer.fr";

        Properties props = System.getProperties();

        props.put("mail.smtp.host", smtpHostServer);

        Session session = Session.getInstance(props, null);

        EmailUtil.sendEmail(session, emailID,"File Monitor : Récapitulatif ", "" + ScanRepertoire(repertoireVise) +"");
    }

}
