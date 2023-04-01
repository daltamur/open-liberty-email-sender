package com.email.rest;

import jakarta.mail.*;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

@Path("send")
public class Resource {

    @POST
    public Response getProperties() throws MessagingException, UnsupportedEncodingException {
        String to="<receiver email>";
        String from="<sender email>";
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        Session session = Session.getInstance(prop, new jakarta.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("<sender email>", "<App password>");

            }

        });

        String msgBody = "This is how you send mail through a docker container";

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from, "NoReply"));
            msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to, "<Receiver's name (this can be anything you want)>"));
            msg.setSubject("Here's an example");
            msg.setText(msgBody);
            Transport.send(msg);
            System.out.println("Email sent successfully...");
        } catch (AddressException e) {
            throw new RuntimeException(e);
        }

        return Response.ok().build();
    }

}