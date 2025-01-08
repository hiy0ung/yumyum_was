package org.koreait.yumyum.service;

import lombok.RequiredArgsConstructor;
import org.koreait.yumyum.entity.User;
import org.koreait.yumyum.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 로깅 추가
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public boolean isUserIdMatched(String userEmail) {
        try {
            // 입력값 로깅
            logger.info("[입력값] userEmail: {}", userEmail);

            // 이메일로 사용자 조회
            Optional<User> userByEmail = userRepository.findByUserEmail(userEmail);

            if (userByEmail.isPresent()) {
                // 사용자 존재
                Long userIdByEmail = userByEmail.get().getId();
                logger.info("[조회 성공] userEmail으로 찾은 userId: {}", userIdByEmail);
                return true; // 이메일 존재
            }

            // 조회 실패
            logger.warn("[조회 실패] userEmail로 사용자 정보 없음: {}", userEmail);
            return false;

        } catch (Exception e) {
            // 예외 처리
            logger.error("[오류 발생] {}", e.getMessage());
            return false; // 예외 발생 시 false 반환
        }
    }
}
