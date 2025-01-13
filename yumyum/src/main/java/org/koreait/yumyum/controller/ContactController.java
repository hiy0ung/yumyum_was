package org.koreait.yumyum.controller;

import lombok.RequiredArgsConstructor;
import org.koreait.yumyum.dto.contact.request.ContactRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ContactController {

    private final MailSender mailSender;

    @PostMapping("/contact")
    public ResponseEntity<Map<String, Object>> sendContactMail(
            @AuthenticationPrincipal Long userId,
            @RequestBody ContactRequestDto contactRequest
    ) {

        Map<String, Object> responseData = new HashMap<>();

        try {
            String toEmail = "yumyumgroupmaster@gmail.com";

            String userEmail = contactRequest.getEmail();
            String title = contactRequest.getTitle();
            String message = contactRequest.getMessage();

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(toEmail);
            mailMessage.setSubject("[문의] " + title);
            mailMessage.setText(
                    "답장 받을 이메일 주소 : " + userEmail + "\n\n"
                            + "문의 내용:\n" + message
            );

            mailSender.send(mailMessage);
            responseData.put("data", "success");
            return ResponseEntity.ok(responseData);

        } catch (Exception e) {
            e.printStackTrace();
            responseData.put("data", "fail");
            return ResponseEntity.status(500).body(responseData);
        }
    }
}