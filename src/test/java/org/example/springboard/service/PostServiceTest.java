package org.example.springboard.service;

import org.example.springboard.dto.PostRequest;
import org.example.springboard.entity.Post;
import org.example.springboard.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PostServiceTest {

    @Autowired
    PostRepository postRepository;
    @Autowired
    PostService postService;

    @Test
    void 게시글_저장_테스트(){
        PostRequest postRequest = new PostRequest("제목", "내용");
        postService.addPost(postRequest);
        Post result = postRepository.findByTitle("제목");
        assertAll(
                ()-> assertEquals(postRequest.getTitle(),result.getTitle()),
                ()->assertEquals(postRequest.getContent(),result.getContent())

        );
    }
}