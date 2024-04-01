package apiemailsender.service;

import apiemailsender.model.Email;
import apiemailsender.model.enums.StatusEmail;
import apiemailsender.repository.EmailRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class EmailService {

    private final EmailRepository repository;
    private final JavaMailSender mailSender;

    public Email sendEmail(Email email) {
        email.setSendDateEmail(LocalDateTime.now());
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email.getEmailFrom());
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());
            mailSender.send(message);
            email.setStatusEmail(StatusEmail.SENT);
        } catch(MailException e){
            email.setStatusEmail(StatusEmail.ERROR);
        }finally{
            return repository.save(email);
        }
    }
}
