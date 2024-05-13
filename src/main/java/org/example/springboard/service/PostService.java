package org.example.springboard.service;

import lombok.RequiredArgsConstructor;
import org.example.springboard.entity.Post;
import org.example.springboard.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void addPost(Post post){
        postRepository.save(post);
    }

    public Page<Post> findAll(Pageable pageable){
        return postRepository.findAllByOrderByIdDesc(pageable);
    }

    public Post findById(Long id){
        return postRepository.findById(id).get();
    }


}
