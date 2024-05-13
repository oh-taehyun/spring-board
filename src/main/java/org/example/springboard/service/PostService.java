package org.example.springboard.service;

import lombok.RequiredArgsConstructor;
import org.example.springboard.dto.PostRequest;
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

    public void addPost(PostRequest postRequest){
        Post post = new Post(postRequest.getTitle(),postRequest.getContent());
        postRepository.save(post);
    }

    public Page<Post> findAll(Pageable pageable){
        return postRepository.findAllByOrderByIdDesc(pageable);
    }

    public Post findById(Long id){
        return postRepository.findById(id).get();
    }

    public void update(Long postId, PostRequest postRequest){
        Post post = new Post(postId,postRequest.getTitle(),postRequest.getContent());

    }
    public void delete(Long postId){
        postRepository.deleteById(postId);
    }

}
