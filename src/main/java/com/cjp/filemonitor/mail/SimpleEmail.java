package com.cjp.filemonitor.mail;


import javax.mail.Session;
import java.util.Properties;


public class SimpleEmail {

    public static void main(String[] args) {

        System.out.println("SimpleEmail Start");

        String smtpHostServer = "owa.clermont.unicancer.fr";
        String emailID = "Alexandre.CHAMPAGNAC@clermont.unicancer.fr";

        Properties props = System.getProperties();

        props.put("mail.smtp.host", smtpHostServer);

        Session session = Session.getInstance(props, null);

        EmailUtil.sendEmail(session, emailID,"Hello World . Its dangerous to go alone, take this", "This is a test : If This mail appear its good sign");
    }

}
