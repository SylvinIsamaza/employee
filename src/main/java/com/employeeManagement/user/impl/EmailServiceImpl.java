package com.employeeManagement.user.impl;

import com.employeeManagement.user.services.EmailService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

// sending email
@Service
@Data
@Slf4j
public class EmailServiceImpl implements EmailService {
    private  final JavaMailSender mailSender;
    @Autowired
    @Value("${spring.mail.username}")
    private String from ;
    @Override
    public void sendSimpleEmailMessage(String to, String subject, String text) {
        SimpleMailMessage simpleMailMessage= new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
     simpleMailMessage.setTo(to);
     simpleMailMessage.setSubject(subject);
     simpleMailMessage.setText(text);
     mailSender.send(simpleMailMessage);
      log.info(" email send successfully");
    }
}
