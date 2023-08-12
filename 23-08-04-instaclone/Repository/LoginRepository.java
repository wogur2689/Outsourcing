package com.goorm.mungge.instagram.Repository;

import com.goorm.mungge.instagram.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository {
    /* 회원가입 */
    //가입한 사람인지 조회
    int signUpDataSelect(String email, String phone);
    //회원가입
    void signUpDataInsert(User user);
    //회원정보 조회(로그인)
    User loginDataSelect(String email, String password);
    //비밀번호 재설정
    void passwordUpdate(String email, String password);
}
