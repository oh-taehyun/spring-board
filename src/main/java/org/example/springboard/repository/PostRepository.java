package org.example.springboard.repository;

import org.example.springboard.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    Post findByTitle(String title);
    Page<Post> findAllByOrderByIdDesc(Pageable pageable);

}
