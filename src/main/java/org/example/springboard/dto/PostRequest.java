package org.example.springboard.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class PostRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String content;

    public PostRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
