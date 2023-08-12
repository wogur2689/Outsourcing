package com.goorm.mungge.instagram.Controller;

import com.goorm.mungge.instagram.Service.LoginService;
import com.goorm.mungge.instagram.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private LoginService loginService;

    //비밀번호 재설정
    @PostMapping("/password-reset")
    public ResponseEntity<?> passwordReset(@RequestBody User user) {
        //password 수정
        String message = loginService.passwordUpdate(user);

        //결과 반환
        return new ResponseEntity<>(message, getHttpHeader(), HttpStatus.OK);
    }

    //회원가입
    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody User user) {
        //회원가입
        String message = loginService.signUp(user);

        //결과 반환
        return new ResponseEntity<>(message, getHttpHeader(), HttpStatus.OK);
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        //DB에 저장된 정보와 맞는지 확인
        String message = loginService.login(user.getEmail(), user. getPassword());

        //결과 반환
        return new ResponseEntity<>(message, getHttpHeader(), HttpStatus.OK);
    }

    //이메일 인증
    @PostMapping("/email-auth")
    public ResponseEntity<?> emailAuth(@RequestBody String email){
        //1. 이메일 형식이 맞는지 체크
        if(isValidEmail(email)) {
            return new ResponseEntity<>("이메일 형식이 아닙니다.", getHttpHeader(), HttpStatus.OK);
        }

        //2. 랜덤 인증번호 생성
        Random random = new Random();
        String code = String.valueOf(random.nextInt(1000, 10000));

        //결과 반환
        return new ResponseEntity<>(code, getHttpHeader(), HttpStatus.OK);
    }

    //공통 헤더
    private HttpHeaders getHttpHeader() {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return header;
    }

    //이메일 검증
    public static boolean isValidEmail(String email) {
        boolean err = false;
        String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        if(m.matches()) {
            err = true;
        }
        return err;
    }
}
