package org.koreait.yumyum.service.implement;

import lombok.RequiredArgsConstructor;
import org.koreait.yumyum.common.constant.ResponseMessage;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.password.ChangePasswordRequestDto;
import org.koreait.yumyum.entity.User;
import org.koreait.yumyum.provider.JwtProvider;
import org.koreait.yumyum.repository.UserRepository;
import org.koreait.yumyum.service.PasswordService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class PasswordServiceImpl implements PasswordService {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 비밀번호 정규식 (최소 8자, 대문자, 소문자, 숫자, 특수문자 포함)
    private static final String PASSWORD_PATTERN =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    @Override
    public ResponseDto<String> changePassword(ChangePasswordRequestDto dto) {
        try {
            // 1. 토큰 검증 및 이메일 조회
            String email = jwtProvider.validateAndGetUserEmail(dto.getToken());
            // 2. 이메일로 사용자 조회
            Optional<User> optionalUser = userRepository.findByUserId(email);
            // 사용자 없음
            if (optionalUser.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }
            User user = optionalUser.get();
            // 3. 비밀번호 검증
            if (!Pattern.matches(PASSWORD_PATTERN, dto.getNewPassword())) {
                return ResponseDto.setFailed("비밀번호는 최소 8자 이상, 대문자, 소문자, 숫자, 특수문자를 포함해야 합니다.");
            }
            if (!dto.getNewPassword().equals(dto.getConfirmPassword())) {
                return ResponseDto.setFailed("비밀번호가 일치하지 않습니다.");
            }
            // 4. 비밀번호 암호화 및 저장
            String encodedPassword = passwordEncoder.encode(dto.getNewPassword());
            user.setEncodedPassword(encodedPassword);
            userRepository.save(user);

            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, "비밀번호가 성공적으로 변경되었습니다.");

        } catch (IllegalArgumentException e) {
            return ResponseDto.setFailed(e.getMessage());
        } catch (Exception e) {
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }
}
