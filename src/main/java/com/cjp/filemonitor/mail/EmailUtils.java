package com.cjp.filemonitor.mail;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class EmailUtils {

    private final String toEmail;
    private final String smtpHostServer;
    private Session session;


    public EmailUtils(String toEmail, String smtpHostServer) {
        this.toEmail = toEmail;
        this.smtpHostServer = smtpHostServer;
    }

    public void createSession(){
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", smtpHostServer);

        this.session = Session.getInstance(properties,null);

    }

    public void sendEmail(String subject, String body) throws MessagingException, IOException {


        MimeMessage email = new MimeMessage(session);


        //Config Message Header

        email.addHeader("Content-type", "text/HTML; charset=UTF-8");
        email.addHeader("format", "flowed");
        email.addHeader("Content-Transfer-Encoding", "8bit");


        //Config the expeditor
        email.setFrom(new InternetAddress("filemonitor@noreply.com", "NoReply-FileMonitor"));

        email.setReplyTo(InternetAddress.parse("no_reply@example.com", false));
        email.setSubject(subject, "UTF-8");
        email.setContent(body, "text/html; charset=UTF-8");

        email.setSentDate(new Date());


        //Send the message
        email.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
        System.out.println("Message is config");
        Transport.send(email);

        System.out.println("Email Sent !");

    }


}
