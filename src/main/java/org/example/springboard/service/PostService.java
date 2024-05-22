package org.example.springboard.service;

import lombok.RequiredArgsConstructor;
import org.example.springboard.auth.CustomUserDetailService;
import org.example.springboard.dto.PostRequest;
import org.example.springboard.entity.Post;
import org.example.springboard.entity.UserAccount;
import org.example.springboard.exception.ResourceNotFoundException;
import org.example.springboard.exception.UnauthorizedException;
import org.example.springboard.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CustomUserDetailService userDetailService;

    public void addPost(UserAccount user,PostRequest postRequest){
        Post post = new Post(user,postRequest.getTitle(),postRequest.getContent());
        postRepository.save(post);
    }

    public Page<Post> findAll(Pageable pageable){
        return postRepository.findAllByOrderByPostIdDesc(pageable);
    }

    public Post findById(Long id){
        return postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("존재하지 않는 글입니다."));
    }

    public void update(Long postId, PostRequest postRequest){
        Post post = findById(postId);
        if(!post.getUser().getUsername().equals(userDetailService.getCurrentUsername())){
            throw new UnauthorizedException("작성자만 수정할 수 있습니다.");
        }
        post.setTitle(postRequest.getTitle());
            post.setContent(postRequest.getContent());
            postRepository.save(post);

    }
    public void delete( Long postId) {
        Post post = findById(postId);
        if(!post.getUser().getUsername().equals(userDetailService.getCurrentUsername())){
            throw new UnauthorizedException("작성자만 삭제할 수 있습니다.");
        }
            postRepository.deleteById(postId);
    }

}
