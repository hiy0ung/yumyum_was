package org.koreait.yumyum.controller;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.mail.IdSendMailRequestDto;
import org.koreait.yumyum.dto.mail.PasswordSendMailRequestDto;
import org.koreait.yumyum.service.MailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mail")
public class MailController {

    private final MailService mailService;

    @PostMapping("/send/password")
    public ResponseEntity<ResponseDto<String>> passwordSendEmail(@RequestBody PasswordSendMailRequestDto dto) throws MessagingException {
        ResponseDto<String> response = mailService.passwordSendMessage(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @PostMapping("/send/id")
    public ResponseEntity<ResponseDto<String>> idSendEmail(@RequestBody IdSendMailRequestDto dto) throws MessagingException {
        ResponseDto<String> response = mailService.idSendMessage(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

}
