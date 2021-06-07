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

    public void sendEmail(String subject, String body, String toEmail) throws MessagingException, IOException {

        Properties properties = System.getProperties();
        Session session = Session.getInstance(properties, null);
        String smtpHostServer = "owa.clermont.unicancer.fr";
        properties.put("mail.smtp.host", smtpHostServer);
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
