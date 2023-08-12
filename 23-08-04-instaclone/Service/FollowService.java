package com.goorm.mungge.instagram.Service;

import com.goorm.mungge.instagram.Repository.FollowRepositoryImpl;
import com.goorm.mungge.instagram.domain.FollowMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
public class FollowService {

    @Autowired
    private FollowRepositoryImpl followRepositoryImpl;

    //팔로잉
    public String following(Long followerUserId, Long followingUserId) {
        String msg = "팔로잉 성공";
        try {
            followRepositoryImpl.following(followerUserId, followingUserId);
        } catch (Exception e) {
            e.printStackTrace();
            msg = "시스템 오류";
        }
        return msg;
    }

    //팔로잉 리스트
    public ArrayList<FollowMapping> followingList(Long followerUserId) {
        ArrayList<FollowMapping> followingList = new ArrayList<>();
        try {
            followingList = followRepositoryImpl.followingList(followerUserId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return followingList;
    }

    //팔로워 리스트
    public ArrayList<FollowMapping> followerList(Long followingUserId) {
        ArrayList<FollowMapping> followerList = new ArrayList<>();
        try {
            followerList = followRepositoryImpl.followerList(followingUserId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return followerList;
    }

    // 팔로잉, 팔로워 삭제
    public String followDelete(Long followerUserId, Long followingUserId) {
        String message = "처리되었습니다.";
        try {
            followRepositoryImpl.followDelete(followerUserId, followingUserId);
        } catch (Exception e) {
            e.printStackTrace();
            message = "시스템 에러";
        }
        return message;
    }
}
