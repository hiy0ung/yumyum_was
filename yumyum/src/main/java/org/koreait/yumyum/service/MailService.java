package org.koreait.yumyum.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.mail.IdSendMailRequestDto;
import org.koreait.yumyum.dto.mail.PasswordSendMailRequestDto;

public interface MailService {
    MimeMessage passwordCreateMail(String mail, String token) throws MessagingException;
    ResponseDto<String> passwordSendMessage(PasswordSendMailRequestDto dto) throws MessagingException;


    MimeMessage idCreateMail(String mail, String userName) throws MessagingException;
    ResponseDto<String> idSendMessage(IdSendMailRequestDto dto) throws MessagingException;

}
