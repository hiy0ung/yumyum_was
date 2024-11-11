package org.koreait.yumyum.service.implement;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.koreait.yumyum.common.constant.ResponseMessage;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.auth.request.LoginRequestDto;
import org.koreait.yumyum.dto.auth.request.SignUpRequestDto;
import org.koreait.yumyum.dto.auth.response.LoginResponseDto;
import org.koreait.yumyum.dto.auth.response.SignUpResponseDto;
import org.koreait.yumyum.entity.User;
import org.koreait.yumyum.provider.JwtProvider;
import org.koreait.yumyum.repository.UserRepository;
import org.koreait.yumyum.service.AuthService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptpasswordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public ResponseDto<SignUpResponseDto> signUp(@Valid SignUpRequestDto dto){
        String userId = dto.getUserId();
        String userPw = dto.getUserPw();
        String checkPw = dto.getCheckPw();
        String userName = dto.getUserName();
        String userEmail = dto.getUserEmail();
        String userPhone = dto.getUserPhone();
        String userBusinessNumber = dto.getUserBusinessNumber();
        boolean privacyPolicyAgreed = dto.isPrivacyPolicyAgreed();
        boolean marketingAgreed = dto.isMarketingAgreed();

        SignUpResponseDto data = null;

        if (userId == null || userId.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_USER_ID);
        }

        if (userPw == null || userPw.isEmpty() || checkPw == null || checkPw.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_PASSWORD);
        }

        if (!userPw.equals(checkPw)) {
            return ResponseDto.setFailed(ResponseMessage.NOT_MATCH_PASSWORD);
        }

        if (userPw.length() < 8 || !userPw.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*])[a-zA-Z\\d!@#$%^&*]{10,}$")) {
            return ResponseDto.setFailed(ResponseMessage.WEAK_PASSWORD);
        }

        if (userName == null || userName.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_NAME);
        }

        if (userEmail == null || userEmail.isEmpty() || !EmailValidator.getInstance().isValid(userEmail)) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_EMAIL);
        }

        if (userPhone == null || userPhone.isEmpty() || !userPhone.matches("^\\d{10,11}$")) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_PHONE);
        }

        if (userBusinessNumber == null || userBusinessNumber.isEmpty() || !userBusinessNumber.matches("^\\d{10}$")) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_PHONE);
        }

        if (userRepository.existsByUserId(userId)) {
            return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);
        }

        if (userRepository.existsByUserEmail(userEmail)) {
            return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);
        }

        if (userRepository.existsByUserBusinessNumber(userBusinessNumber)) {
            return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);
        }

        try {
            String encodePassword = bCryptpasswordEncoder.encode(userPw);
            User user = User.builder()
                    .userId(userId)
                    .userPw(encodePassword)
                    .userName(userName)
                    .userEmail(userEmail)
                    .userPhone(userPhone)
                    .userBusinessNumber(userBusinessNumber)
                    .privacyPolicyAgreed(privacyPolicyAgreed)
                    .marketingAgreed(marketingAgreed)
                    .build();

            userRepository.save(user);

            data = new SignUpResponseDto(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<LoginResponseDto> login(@Valid LoginRequestDto dto) {
        String userId = dto.getUserId();
        String userPw = dto.getUserPw();

        LoginResponseDto data = null;

        if(userId == null || userId.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_USER_ID);
        }

        if(userPw == null || userPw.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_PASSWORD);
        }

        try {
            User user = userRepository.findByUserId(userId)
                    .orElse(null);

            if (user == null) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);
            }

            if (!bCryptpasswordEncoder.matches(userPw, user.getUserPw())) {
                return ResponseDto.setFailed(ResponseMessage.NOT_MATCH_PASSWORD);
            }

            String token = jwtProvider.generateJwtToken(userId);
            int exprTime = jwtProvider.getExpiration();
            data = new LoginResponseDto(token, exprTime);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
