package com.goorm.mungge.instagram.Service;

import com.goorm.mungge.instagram.Repository.LoginRepositoryImpl;
import com.goorm.mungge.instagram.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoginService {

    @Autowired
    private LoginRepositoryImpl loginRepositoryImpl;

    public String passwordUpdate(User user) {
        String msg = "비밀번호 변경이 완료되었습니다.!";

        try {
            // 비밀번호 수정
            loginRepositoryImpl.passwordUpdate(user.getEmail(), user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            msg = "시스템 오류";
        }
        return msg;
    }

    //회원가입
    public String signUp(User user) {
        String msg = "회원가입 완료되었습니다.!";

        try {
            //1. 이미 가입한 사람인지 조회
            if(loginRepositoryImpl.signUpDataSelect(user.getEmail(), user.getPhone()) > 0) {
                msg = "이미 가입하셨습니다.";
                return msg;
            }
            //2. 회원가입
            loginRepositoryImpl.signUpDataInsert(user);
        } catch (Exception e) {
            e.printStackTrace();
            msg = "시스템 오류";
        }
        return msg;
    }


    //로그인
    public String login(String email, String pw) {
        String msg = "로그인되었습니다.";

        try {
            //1. DB 데이터 조회
            User user = loginRepositoryImpl.loginDataSelect(email, pw);
            log.info("User : {}", user);

            //2. id(email) 및 비번이 일치하는지 확인
            if(user.getEmail().equals(email)) {
                log.info("ID(Email) 일치");
                if(user.getPassword().equals(pw)) {
                    log.info("password 일치");
                }
                else {
                    msg = "비밀번호가 틀렸습니다.";
                }
            } else {
                msg = "아이디가 틀렸습니다.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg =  "시스템 에러";
        }
        return msg;
    }
}
