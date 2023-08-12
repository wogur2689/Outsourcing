package com.goorm.mungge.instagram.Repository;

import com.goorm.mungge.instagram.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;

@Slf4j
@Service
public class LoginRepositoryImpl implements LoginRepository {
    private final DataSource dataSource;

    //생성자
    public LoginRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //가입한 사람인지 확인
    @Override
    @Transactional(readOnly = true)
    public int signUpDataSelect(String email, String phone) {
        //sql 작성 및 변수 선언
        String sql = "select * from users where Email = ? AND Phone = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int cnt = 0;

        try {
            //db 연결
            conn = getConnection();
            //PreparedStatement 객체 생성
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, phone);
            //결과 받아오기
            rs = pstmt.executeQuery();

            while (rs.next()) {
                log.info("결과값 : " + rs);
                cnt = rs.getRow();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //db 연결 끊기
            close(conn, pstmt, rs);
        }
        return cnt;
    }

    //회원정보 저장
    @Override
    @Transactional
    public void signUpDataInsert(User user) {
        //sql 작성 및 변수 선언
        String sql = "insert into users(email, phone, password, nickname, profileIntro, profilePhotoUrl, createdAt, updatedAt, isDeleted)" +
                "value (?, ?, ?, ?, ?, ?, current_timestamp, current_timestamp, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        log.info("회원가입");

        try {
            //db 연결
            conn = getConnection();
            //PreparedStatement 객체 생성
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getPhone());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getNickname());
            pstmt.setString(5, user.getProfileIntro());
            pstmt.setString(6, user.getProfilePhotoUrl());
            pstmt.setBoolean(7, user.getIsDeleted());
            //쿼리 실행
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //db 연결 끊기
            close(conn, pstmt, rs);
        }
    }

    //사용자가 입력한 정보를 바탕으로 회원 정보 조회
    @Override
    @Transactional(readOnly = true)
    public User loginDataSelect(String email, String pw) {
        //sql 작성 및 변수 선언
        String sql = "select * from users where email = ? limit 1";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = new User();

        try {
            //db 연결
            conn = getConnection();
            //PreparedStatement 객체 생성
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            //결과 받아오기
            rs = pstmt.executeQuery();

            while (rs.next()) {
                user.setId(rs.getLong("id"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setPassword(rs.getString("password"));
                user.setNickname(rs.getString("nickname"));
                user.setProfileIntro(rs.getString("profileIntro"));
                user.setProfilePhotoUrl(rs.getString("profilePhotoUrl"));
                user.setIsDeleted(rs.getBoolean("isDeleted"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //db 연결 끊기
            close(conn, pstmt, rs);
        }
        return user;
    }

    //비밀번호 재설정
    @Override
    public void passwordUpdate(String email, String password) {
        //sql 작성 및 변수 선언
        String sql = "update users set password = ?, updatedAt = current_timestamp where email = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            //db 연결
            conn = getConnection();
            //PreparedStatement 객체 생성
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, password);
            pstmt.setString(2, email);
            //실행
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //db 연결 끊기
            close(conn, pstmt, rs);
        }
    }

    //DB 연결
    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }

    //DB연결 끊기
    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                log.info("close ResultSet");
                rs.close();
            }
            if (pstmt != null) {
                log.info("close PreparedStatement");
                pstmt.close();
            }
            if (conn != null) {
                log.info("close Connection");
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
