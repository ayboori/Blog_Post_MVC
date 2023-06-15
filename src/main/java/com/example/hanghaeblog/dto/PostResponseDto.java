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
    private String writeDate;
    private String password;
    private LocalDate localDate;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.userName = post.getUserName();
        this.content = post.getContent();
        this.writeDate = post.getWriteDate();
        this.password = post.getPassword();
        this.localDate = post.getLocalDate();
    }
}
