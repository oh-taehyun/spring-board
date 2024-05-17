package org.example.springboard.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springboard.dto.PostRequest;
import org.example.springboard.entity.Post;
import org.example.springboard.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 *  게시글을 담당하는 컨트롤러
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;
    @GetMapping("/write")
    public String write(@ModelAttribute("post")PostRequest postRequest)
    {
        return "writeForm";

    }

    @PostMapping("write")
    public String addPost(@Validated @ModelAttribute("post") PostRequest post,
                          BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.info("errors={}",bindingResult);
            return "writeForm";
        }
        //성공 로직
        postService.addPost(post);
        return "redirect:/";
    }

    @GetMapping("/")
    public String postList(Model model, @PageableDefault(size = 3) Pageable pageable){
       Page<Post>list = postService.findAll(pageable);
        paging(model, pageable, list);
        return "index";
    }

    private void paging(Model model, Pageable pageable, Page<Post> list) {
        int startPage;
        int endPage;
        if(list.getTotalPages() < 5){
            startPage=1;
            endPage = list.getTotalPages();
        } else if (pageable.getPageNumber() <3) {
            startPage = 1;
            endPage = 5;
        } else if (pageable.getPageNumber() >= list.getTotalPages() -3) { //현재 페이지가 총 페이지 -3 보다 크거나 같을 때는
            startPage = 1;
            endPage = list.getTotalPages();
        }
        else {
            startPage = list.getPageable().getPageNumber() -1; //4페이지 보일 때 원래 페이지는 3이므로 1을 빼서 2로 설정
            endPage = list.getPageable().getPageNumber() +3; // 원래ㅐ 페이지인 3에 3을 더해 6으로 설정(2,3,4,5,6)까지 설정 가능
        }
        model.addAttribute("list", list);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
    }


    @GetMapping("posts/{id}")
    public String getPost(@PathVariable Long id, Model model){
        Post post=postService.findById(id);
        model.addAttribute("post",post);
        return "postView";
    }

    @GetMapping("/posts/{id}/update")
    public String getPostUpdate( @PathVariable Long id,Model model){
        Post post = postService.findById(id);
        model.addAttribute("post",post);
        return "updateForm";
    }

    @PostMapping("/posts/{id}/update")
    public String postUpdate(@PathVariable Long id, @Validated @ModelAttribute("post") PostRequest postRequest,
                             BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("errors={}",bindingResult);
            return "updateForm";
        }
        postService.update(id,postRequest);
        return "redirect:/posts/"+id;
    }

    @GetMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable Long id){
        postService.delete(id);
        return "redirect:/";
    }
}
