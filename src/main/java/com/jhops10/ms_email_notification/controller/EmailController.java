package com.jhops10.ms_email_notification.controller;

import com.jhops10.ms_email_notification.business.EmailService;
import com.jhops10.ms_email_notification.business.dto.TaskDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notification")
public class EmailController {

    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<Void> sendEmailNotification(@RequestBody TaskDTO dto) {
       emailService.sendEmailNotification(dto);
       return ResponseEntity.ok().build();
    }
}
