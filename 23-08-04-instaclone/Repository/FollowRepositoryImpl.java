package com.goorm.mungge.instagram.Repository;

import com.goorm.mungge.instagram.domain.FollowMapping;
import com.goorm.mungge.instagram.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Slf4j
@Service
public class FollowRepositoryImpl implements FollowRepository {

    private final DataSource dataSource;

    //생성자
    public FollowRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //팔로우매핑 데이터 저장
    @Override
    @Transactional
    public void following(Long followerUserId, Long followingUserId) {
        //sql 작성 및 변수 선언
        String sql = "insert into followmapping(followeruserid, followinguserid)" +
                "values (?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            //db 연결
            conn = getConnection();
            //PreparedStatement 객체 생성
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, followerUserId);
            pstmt.setLong(2, followingUserId);
            //insert
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //db 연결 끊기
            close(conn, pstmt, rs);
        }
    }

    //팔로워 리스트 출력(팔로잉 유저 id 출력)
    @Override
    @Transactional(readOnly = true)
    public ArrayList<FollowMapping> followingList(Long followerUserId) {
        //sql 작성 및 변수 선언
        String sql = "select * from followmapping where followeruserid = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<FollowMapping> followMappingList = new ArrayList<>();

        try {
            //db 연결
            conn = getConnection();
            //PreparedStatement 객체 생성
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, followerUserId);
            //insert
            rs = pstmt.executeQuery();

            while (rs.next()) {
                FollowMapping followMapping = new FollowMapping();
                followMapping.setFollowingUserId(rs.getLong("followingUserId"));
                followMapping.setFollowerUserId(rs.getLong("followerUserId"));
                followMappingList.add(followMapping);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //db 연결 끊기
            close(conn, pstmt, rs);
        }
        return followMappingList;
    }


    //팔로잉 리스트 출력(팔로잉 유저 id 출력)
    @Override
    @Transactional(readOnly = true)
    public ArrayList<FollowMapping> followerList(Long followingUserId) {
        //sql 작성 및 변수 선언
        String sql = "select * from followmapping where followinguserid = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<FollowMapping> followMappingList = new ArrayList<>();

        try {
            //db 연결
            conn = getConnection();
            //PreparedStatement 객체 생성
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, followingUserId);
            //insert
            rs = pstmt.executeQuery();

            while (rs.next()) {
                FollowMapping followMapping = new FollowMapping();
                followMapping.setFollowingUserId(rs.getLong("followingUserId"));
                followMapping.setFollowerUserId(rs.getLong("followerUserId"));
                followMappingList.add(followMapping);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //db 연결 끊기
            close(conn, pstmt, rs);
        }
        return followMappingList;
    }

    //팔로잉 및 팔로워 삭제
    @Override
    public void followDelete(Long followerUserId, Long followingUserId) {
        //sql 작성 및 변수 선언
        String sql = "delete from followmapping where followeruserid = ? AND followinguserid = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            //db 연결
            conn = getConnection();
            //PreparedStatement 객체 생성
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, followerUserId);
            pstmt.setLong(2, followingUserId);
            //insert
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
