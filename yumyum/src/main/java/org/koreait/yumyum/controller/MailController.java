package org.koreait.yumyum.controller;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.koreait.yumyum.dto.mail.SendMailRequestDto;
import org.koreait.yumyum.service.MailService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mail")
public class MailController {
    private final MailService mailService;

    @PostMapping("/send")
    public String sendEmail(@RequestBody SendMailRequestDto mailDto) throws MessagingException {

        return mailService.sendSimpleMessage(mailDto.getEmail(), mailDto.getUsername());
    }
    @GetMapping("/verify")
    public String verifyEmail(@RequestParam String token) {
        return mailService.verifyEmail(token);
    }


}
