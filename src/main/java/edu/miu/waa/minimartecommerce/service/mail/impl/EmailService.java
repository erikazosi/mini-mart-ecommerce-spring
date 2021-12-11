package edu.miu.waa.minimartecommerce.service.mail.impl;

import edu.miu.waa.minimartecommerce.exceptionHandling.exceptions.EmailException;
import edu.miu.waa.minimartecommerce.service.mail.IEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Service
public class EmailService implements IEmailService {
    @Value("${email.alias.name}")
    private String aliasName;

    private final Logger logger = LoggerFactory.getLogger(EmailService.class);
    private static final int noOfQuickServiceThreads = 20;

    /**
     * this statement create a thread pool of twenty threads
     * here we are assigning send mail task using ScheduledExecutorService.submit();
     */
    private final ScheduledExecutorService quickService = Executors.newScheduledThreadPool(noOfQuickServiceThreads);

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendMail(String username, String subject, String message) throws MessagingException, UnsupportedEncodingException {
        MimeMessage msg=javaMailSender.createMimeMessage();
        msg.setFrom(new InternetAddress(aliasName, aliasName,"UTF-*"));
        msg.setRecipient(Message.RecipientType.TO,new InternetAddress(username));
        msg.setSubject(subject);
        msg.setContent(message,"text/html; charset=utf-8");
        quickService.submit(() -> {
            try{
                javaMailSender.send(msg);
            }
            catch(Exception ex){
                logger.error(ex.getMessage());
                throw new EmailException(ex.getMessage(),ex);
            }
        });
    }
}
