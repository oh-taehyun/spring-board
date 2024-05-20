package org.example.springboard.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Builder
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String password;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    List<Post> post = new ArrayList<>();

    public UserAccount(Long userId, String username, String password, List<Post> post) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.post = post;
    }
    public UserAccount(Long userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }
}
