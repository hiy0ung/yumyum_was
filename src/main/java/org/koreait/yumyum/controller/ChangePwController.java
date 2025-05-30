package org.koreait.yumyum.controller;

import lombok.RequiredArgsConstructor;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.password.ChangePasswordRequestDto;
import org.koreait.yumyum.service.PasswordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/password")
public class ChangePwController {

    private final PasswordService passwordService;

    @PostMapping("/change")
    public ResponseEntity<ResponseDto<String>> changePassword(@RequestBody ChangePasswordRequestDto dto) {
        if (dto.getToken() == null || dto.getToken().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(ResponseDto.setFailed("토큰이 누락되었습니다."));
        }

        ResponseDto<String> response = passwordService.changePassword(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}
