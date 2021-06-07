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



    private String toEmail;
    private String smtpHostServer;
    private Session session;

    public String getToEmail() {
        return toEmail;
    }


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


        MimeMessage Email = new MimeMessage(session);


        //Config Message Header

        Email.addHeader("Content-type", "text/HTML; charset=UTF-8");
        Email.addHeader("format", "flowed");
        Email.addHeader("Content-Transfer-Encoding", "8bit");


        //Config the expeditor
        Email.setFrom(new InternetAddress("filemonitor@noreply.com", "NoReply-FileMonitor"));

        Email.setReplyTo(InternetAddress.parse("no_reply@example.com", false));
        Email.setSubject(subject, "UTF-8");
        Email.setContent(body, "text/html; charset=UTF-8");

        Email.setSentDate(new Date());


        //Send the message
        Email.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
        System.out.println("Message is config");
        Transport.send(Email);

        System.out.println("Email Sent !");

    }


}
