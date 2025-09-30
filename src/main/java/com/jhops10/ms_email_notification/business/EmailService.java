package com.jhops10.ms_email_notification.business;

import com.jhops10.ms_email_notification.business.dto.TaskDTO;
import com.jhops10.ms_email_notification.infrastructure.exceptions.EmailException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Value("${send.email.from}")
    public String from;

    @Value("${send.email.name}")
    private String name;

    public void sendEmailNotification(TaskDTO dto) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());

            mimeMessageHelper.setFrom(new InternetAddress(from, name));
            mimeMessageHelper.setTo(InternetAddress.parse(dto.userEmail()));
            mimeMessageHelper.setSubject("Notificação de Tarefa");

            Context context = new Context();
            context.setVariable("taskName", dto.taskName());
            context.setVariable("taskDate", dto.taskDate());
            context.setVariable("description", dto.description());

            String template = templateEngine.process("notification", context);
            mimeMessageHelper.setText(template, true);

            javaMailSender.send(message);

        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new EmailException("Erro ao enviar o email", e.getCause());
        }
    }

}
