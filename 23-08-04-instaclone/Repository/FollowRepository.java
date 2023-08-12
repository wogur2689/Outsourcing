package com.goorm.mungge.instagram.Repository;

import com.goorm.mungge.instagram.domain.FollowMapping;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface FollowRepository {
    //팔로잉
    void following(Long followerUserId, Long followingUserId);

    //팔로잉 리스트
    ArrayList<FollowMapping> followingList(Long followerUserId);

    //팔로워 리스트
    ArrayList<FollowMapping> followerList(Long followingUserId);

    //팔로잉, 팔로워 삭제
    void followDelete(Long followerUserId, Long followingUserId);
}
