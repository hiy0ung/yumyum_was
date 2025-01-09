package org.koreait.yumyum.service.implement;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.koreait.yumyum.common.constant.ResponseMessage;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.mail.IdSendMailRequestDto;
import org.koreait.yumyum.dto.mail.PasswordSendMailRequestDto;
import org.koreait.yumyum.provider.JwtProvider;
import org.koreait.yumyum.repository.UserRepository;
import org.koreait.yumyum.service.MailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    @Value("${spring.mail.username}")
    private static String senderEmail;

    @Override
    public MimeMessage passwordCreateMail(String mail, String token) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        message.setFrom(senderEmail); // 발신자 설정
        message.setRecipients(MimeMessage.RecipientType.TO, mail); // 수신자 설정
        message.setSubject("비밀번호 찾기 : 이메일 인증"); // 이메일 제목

        String frontendUrl = "http://localhost:3000/findPassword?token=" + token;

        String body = "";
        body += "<h2> 이메일 인증 링크 입니다 </h2>";
        body += "<a href=\"" + frontendUrl+ "\"> 여기를 클릭하여 비밀번호 변경 페이지로 이동 </a>";
        body += "<p> 감사합니다. <p>";

        message.setText(body, "UTF-8", "html");

        return message;
    }

    @Override
    public ResponseDto<String> passwordSendMessage(PasswordSendMailRequestDto dto) throws MessagingException {
        try {
            boolean userExists = userRepository.existsByUserIdAndUserNameAndUserEmail(
                    dto.getUserId(),
                    dto.getUserName(),
                    dto.getUserEmail()
            );
            if (!userExists) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }
            String token = jwtProvider.generateEmailValidToken(dto.getUserId());
            MimeMessage message = passwordCreateMail(dto.getUserEmail(), token);
            try {
                javaMailSender.send(message);
                return ResponseDto.setSuccess("성공", token);
            } catch (MailException e) {
                e.printStackTrace();
                return ResponseDto.setFailed(ResponseMessage.MESSAGE_SEND_FAIL);
            }
        } catch (MailException e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }

    @Override
    public MimeMessage idCreateMail(String mail, String userIdFind) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        message.setFrom(senderEmail);
        message.setRecipients(MimeMessage.RecipientType.TO, mail);
        message.setSubject("아이디 찾기 : 이메일 인증");

        String frontendUrl = "http://localhost:3000/findId?" + userIdFind;

        String body = "";
        body += "<h2> 이메일 인증 링크 입니다 </h2>";
        body += "<a href=\"" + frontendUrl+ "\"> 여기를 클릭하여 아이디 확인 페이지로 이동 </a>";
        body += "<p> 감사합니다. <p>";

        message.setText(body, "UTF-8", "html");

        return message;
    }

    @Override
    public ResponseDto<String> idSendMessage(IdSendMailRequestDto dto) throws MessagingException {
        try {
            boolean userExists = userRepository.existsByUserNameAndUserEmail(
                    dto.getUserName(),
                    dto.getUserEmail()
            );
            if (!userExists) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            };
            String userIdFind = userRepository.findByUserNameAndUserEmail(
                    dto.getUserName(),
                    dto.getUserEmail()
            );


            String token = jwtProvider.generateEmailValidToken(dto.getUserEmail());
            MimeMessage message = idCreateMail(dto.getUserEmail(),userIdFind);
            
            try {
                javaMailSender.send(message);
                return ResponseDto.setSuccess("성공", token);
            } catch (MailException e) {
                e.printStackTrace();
                return ResponseDto.setFailed(ResponseMessage.MESSAGE_SEND_FAIL);
            }
        }catch (MailException e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }

    @Override
    public ResponseDto<String> verifyEmail(String token) {
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, token);
    }
}
