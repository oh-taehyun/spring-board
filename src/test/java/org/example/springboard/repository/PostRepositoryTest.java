package org.example.springboard.repository;

import org.example.springboard.entity.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Test
    void 게시글_등록_테스트() {
        Post post = new Post(1L,"제목","내용");
        postRepository.save(post);

        assertEquals(post, postRepository.findById(1L).get());
    }

    @Test
    void 게시글_삭제_테스트() {

        Post post = new Post(1L,"제목","내용");
        postRepository.save(post);
        postRepository.delete(post);

        assertEquals(Optional.empty(),postRepository.findById(1L));
    }
}