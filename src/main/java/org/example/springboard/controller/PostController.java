package org.example.springboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *  게시글을 담당하는 컨트롤러
 */
@Controller
public class PostController {

    @GetMapping("/write")
    public String write(){
        return "writeForm";
    }
}
