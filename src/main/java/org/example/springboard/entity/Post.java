package org.example.springboard.entity;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@EqualsAndHashCode
@Getter @Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private String title;
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private UserAccount user;

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Post() {
    }

    public Post( UserAccount user,String title, String content) {
        this.user = user;
        this.title = title;
        this.content = content;
    }

    public Post( Long postId, String title, String content) {
        this.postId=postId;
        this.title = title;
        this.content = content;
    }
}
