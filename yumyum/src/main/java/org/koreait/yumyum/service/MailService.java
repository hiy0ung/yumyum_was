package org.koreait.yumyum.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.mail.SendMailRequestDto;

public interface MailService {
    MimeMessage createMail(String mail, String token) throws MessagingException;
    ResponseDto<String> sendMessage(SendMailRequestDto dto) throws MessagingException;
    ResponseDto<String> verifyEmail(String token);

}
