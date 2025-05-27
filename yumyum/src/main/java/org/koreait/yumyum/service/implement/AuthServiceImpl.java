package org.koreait.yumyum.service.implement;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.koreait.yumyum.common.constant.ResponseMessage;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.auth.request.*;
import org.koreait.yumyum.dto.auth.response.*;
import org.koreait.yumyum.entity.User;
import org.koreait.yumyum.provider.JwtProvider;
import org.koreait.yumyum.repository.UserRepository;
import org.koreait.yumyum.service.AuthService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

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
        String joinPath = dto.getJoinPath();
        String snsId = dto.getSnsId();

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

        if(joinPath == null || joinPath.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL);
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
                    .joinPath(joinPath)
                    .snsId(snsId)
                    .build();

            userRepository.save(user);

            data = new SignUpResponseDto(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<UserIdDuplicationCheckResponseDto> userIdDuplicationCheck(@Valid UserIdDuplicationCheckRequestDto dto) {
        String userId = dto.getUserId();
        UserIdDuplicationCheckResponseDto data;

        try {
            if (userId == null || userId.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.INVALID_USER_ID);
            }
            if (userRepository.existsByUserId(userId)) {
                data = new UserIdDuplicationCheckResponseDto(false);
                return ResponseDto.setSuccess(ResponseMessage.DUPLICATED_USER_ID, data);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        data = new UserIdDuplicationCheckResponseDto(true);
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<UserEmailDuplicationCheckResponseDto> userEmailDuplicationCheck(UserEmailDuplicationCheckRequestDto dto) {
        String userEmail = dto.getUserEmail();
        UserEmailDuplicationCheckResponseDto data;

        try {
            if (userEmail == null || userEmail.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.INVALID_EMAIL);
            }
            if(userRepository.existsByUserEmail(userEmail)) {
                data = new UserEmailDuplicationCheckResponseDto(false);
                return ResponseDto.setSuccess(ResponseMessage.DUPLICATED_USER_EMAIL, data);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        data = new UserEmailDuplicationCheckResponseDto(true);
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<UserBusinessNumberDuplicationCheckResponseDto> userBusinessNumberDuplicationCheck(@Valid UserBusinessNumberDuplicationCheckRequestDto dto) {
        String userBusinessNumber = dto.getUserBusinessNumber();
        UserBusinessNumberDuplicationCheckResponseDto data;
        try {
            if (userBusinessNumber == null || userBusinessNumber.isEmpty() || !userBusinessNumber.matches("^\\d{10}$")) {
                return ResponseDto.setFailed(ResponseMessage.INVALID_PHONE);
            }
            if (userRepository.existsByUserBusinessNumber(userBusinessNumber)) {
                data = new UserBusinessNumberDuplicationCheckResponseDto(false);
                return ResponseDto.setSuccess(ResponseMessage.DUPLICATED_USER_BUSINESS_NUMBER, data);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        data = new UserBusinessNumberDuplicationCheckResponseDto(true);
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

            Long id = user.getId();
            String token = jwtProvider.generateJwtToken(id);
            int exprTime = jwtProvider.getExpiration();
            data = new LoginResponseDto(token, exprTime, user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

}
