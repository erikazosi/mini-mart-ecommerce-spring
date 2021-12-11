package edu.miu.waa.minimartecommerce.service.mail;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface IEmailService {
    void sendMail(String username, String subject, String message) throws MessagingException, UnsupportedEncodingException;
}
