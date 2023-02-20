package ua.nure.hrynko.services;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MyEmailSender {
    public void generateReport(String contentOfTheLetter, String toRecipient) {
        // Add sender
        String from = "admin@gmail.com";
        final String username = "admin";//your Gmail username
        final String password = "admin";//your Gmail password
        Properties props = new Properties();
        props.put("mail.smtp.auth", "false");
        props.put("mail.smtp.starttls.enable", "false");
        props.put("mail.host", "localhost");
        props.put("mail.port", 25);
        props.put("mail.protocol", "smtp");

        // Get the Session object
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object
            Message message = new MimeMessage(session);

            try {
                message.setFrom(new InternetAddress(from));
            } catch (MessagingException ex) {
                ex.printStackTrace();
            }

            try {
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(toRecipient));
            } catch (MessagingException e) {
                e.printStackTrace();
            }

            // Set Subject
            try {
                message.setSubject("Letter from the cruise company");
            } catch (MessagingException ex) {
                ex.printStackTrace();
            }

            // Put the content of your message
            try {
                message.setText(contentOfTheLetter);
            } catch (MessagingException ex) {
                ex.printStackTrace();
            }
            // Send message
            try {
                Transport.send(message);
            } catch (MessagingException ex) {
                throw new RuntimeException(ex);


            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
