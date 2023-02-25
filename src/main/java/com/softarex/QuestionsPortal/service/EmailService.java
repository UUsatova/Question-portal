package com.softarex.QuestionsPortal.service;

import com.softarex.QuestionsPortal.dto.EmailTemplate;
import com.softarex.QuestionsPortal.entity.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailService {

    private String appEmail="uusatova@inbox.ru"; //читать из пропертей
    private final JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;
    private final String emailTemplateLocation="user-%s-email";

    public EmailTemplate getUserCreateEmailTemplate(User user,String templateType){
        Map<String, Object> context = new HashMap<>();
        context.put("firstName",user.getFirstName() );
        context.put("lastName",user.getLastName() );
        context.put("time", LocalDateTime.now());

        EmailTemplate email = new EmailTemplate();
        email.setTo(user.getEmail());
        email.setFrom(appEmail);
        email.setTemplateLocation(String.format(emailTemplateLocation,templateType));
        email.setSubject(templateType+" Q&A account");
        email.setContext(context);
        return email;
    }


    public void sendMail(EmailTemplate email) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        Context context = new Context();
        context.setVariables(email.getContext());
        String emailContent = templateEngine.process(email.getTemplateLocation(), context);

        mimeMessageHelper.setTo(email.getTo());
        mimeMessageHelper.setSubject(email.getSubject());
        mimeMessageHelper.setFrom(email.getFrom());
        mimeMessageHelper.setText(emailContent, true);
        emailSender.send(message);
    }
}