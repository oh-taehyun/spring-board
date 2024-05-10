package org.example.springboard.controller;

import lombok.RequiredArgsConstructor;
import org.example.springboard.entity.Post;
import org.example.springboard.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 *  게시글을 담당하는 컨트롤러
 */
@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    @GetMapping("/write")
    public String write(){
        return "writeForm";
    }

    @PostMapping("write")
    public String addPost(Post post){
        //로직 추가
        postService.addPost(post);
        return "redirect:/";
    }

    @GetMapping("/")
    public String postList(Model model){
        List<Post> list = postService.findAll();

        model.addAttribute("list",list);
        return "index";
    }

    @GetMapping("posts/{id}")
    public String getPost(@PathVariable Long id, Model model){
        Post post=postService.findById(id);
        model.addAttribute("post",post);
        return "postView";
    }
}
