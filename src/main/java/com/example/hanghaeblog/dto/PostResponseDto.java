package com.example.hanghaeblog.dto;


import com.example.hanghaeblog.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto{
    private Long id;
    private String title;
    private String userName;
    private String content;
    private String password;
    private String localDate;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.userName = post.getUserName();
        this.content = post.getContent();
        this.password = post.getPassword();
        this.localDate = post.getTextDate();
    }

    public PostResponseDto(Long id, String userName, String content, String localDate) {
        this.id = id;
        this.userName = userName;
        this.content = content;
        this.localDate = localDate;
    }
}
