package com.goorm.mungge.instagram.Controller;

import com.goorm.mungge.instagram.Service.FollowService;
import com.goorm.mungge.instagram.domain.FollowMapping;
import com.goorm.mungge.instagram.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("/api")
public class FollowController {

    @Autowired
    private FollowService followService;

    //팔로잉
    @PostMapping("/following")
    public ResponseEntity<?> following(@RequestBody FollowMapping followMapping) {
        //DB 저장
        String message = followService.following(followMapping.getFollowingUserId(), followMapping.getFollowerUserId());

        //결과 반환
        return new ResponseEntity<>(message, getHttpHeader(), HttpStatus.OK);
    }

    //팔로잉 목록 출력 (해당기능은 DB 테이블상 구현하기 어려움.)
    @GetMapping("/following-list")
    public ResponseEntity<?> followingList(@RequestBody FollowMapping followMapping) {
        //DB 조회
        ArrayList<FollowMapping> list = followService.followingList(followMapping.getFollowerUserId());

        //결과 반환
        if(list.isEmpty()) return new ResponseEntity<>("팔로잉한 사람이 없습니다.", getHttpHeader(), HttpStatus.OK);

        return new ResponseEntity<>(list, getHttpHeader(), HttpStatus.OK);
    }

    //팔로워 목록 출력
    @GetMapping("/follower-list")
    public ResponseEntity<?> followerList(@RequestBody FollowMapping followMapping) {
        //DB 조회
        ArrayList<FollowMapping> list = followService.followingList(followMapping.getFollowingUserId());

        //결과 반환
        if(list.isEmpty()) return new ResponseEntity<>("팔로워한 사람이 없습니다.", getHttpHeader(), HttpStatus.OK);

        return new ResponseEntity<>(list, getHttpHeader(), HttpStatus.OK);
    }

    //팔로잉, 팔로워 삭제
    @PostMapping("/follow-delete")
    public ResponseEntity<?> followDelete(@RequestBody FollowMapping followMapping) {
        //삭제
        String message = followService.followDelete(followMapping.getFollowerUserId(), followMapping.getFollowingUserId());

        //결과 반환
        return new ResponseEntity<>(message, getHttpHeader(), HttpStatus.OK);
    }


    //공통 헤더
    private HttpHeaders getHttpHeader() {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return header;
    }
}
