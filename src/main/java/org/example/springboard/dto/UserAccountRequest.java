package org.example.springboard.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAccountRequest {

    private String userId;
    private String password;
}
