package org.example.springboard.service;

import org.example.springboard.dto.PostRequest;
import org.example.springboard.dto.UserAccountRequest;
import org.example.springboard.entity.Post;
import org.example.springboard.entity.UserAccount;
import org.example.springboard.repository.PostRepository;
import org.example.springboard.repository.UserAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PostServiceTest {

    @Autowired
    PostRepository postRepository;
    @Autowired
    UserAccountRepository userAccountRepository;
    @Autowired
    PostService postService;

    @BeforeEach
    void setup(){
        UserAccount user = new UserAccount(1L,"aaa","bbb");
        userAccountRepository.save(user);
    }
    @Test
    void 게시글_저장_테스트(){
        Optional<UserAccount> user = userAccountRepository.findById(1L);
        PostRequest postRequest = new PostRequest("제목", "내용");
        postService.addPost(user.get(),postRequest);
        Post result = postRepository.findByTitle("제목");
        assertAll(
                ()-> assertEquals(postRequest.getTitle(),result.getTitle()),
                ()->assertEquals(postRequest.getContent(),result.getContent())

        );
    }
}