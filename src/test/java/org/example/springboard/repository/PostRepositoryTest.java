package org.example.springboard.repository;

import org.example.springboard.entity.Post;
import org.example.springboard.entity.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;
    @Autowired
    UserAccountRepository userAccountRepository;

    @BeforeEach
    void setup(){
        UserAccount user = new UserAccount(1L,"aaa","bbb");
        userAccountRepository.save(user);
    }

    @Test
    void 게시글_등록_테스트() {
        Optional<UserAccount> user = userAccountRepository.findById(1L);
        Post post = new Post(user.get(),"제목","내용");
        postRepository.save(post);

        assertEquals(post, postRepository.findByTitle("제목"));
    }

    @Test
    void 게시글_삭제_테스트() {

        Optional<UserAccount> user = userAccountRepository.findById(1L);
        Post post = new Post(user.get(),"제목","내용");
        postRepository.save(post);
        postRepository.delete(post);

        assertNull(postRepository.findByTitle("제목"));
    }

    @Test
    void 게시글_수정_테스트() {

        Optional<UserAccount> user = userAccountRepository.findById(1L);
        Post post = new Post(user.get(),"제목","내용");
        postRepository.save(post);
        postRepository.findById(post.getPostId());
        Post updatePost = new Post(post.getPostId(), "업데이트된 내용입니다.","업데이트된 내용");
        postRepository.save(updatePost);

        Post result = postRepository.findById(post.getPostId()).get();

        assertAll(
                ()-> assertEquals(updatePost.getTitle(),result.getTitle()),
                ()-> assertEquals(updatePost.getContent(),result.getContent())
        );

        assertEquals(1,postRepository.findAll().size());
    }
}