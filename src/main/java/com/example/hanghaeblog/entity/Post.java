package com.example.hanghaeblog.entity;

import com.example.hanghaeblog.dto.PostRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private Long id;
    private String title;
    private String userName;
    private String content;
    private LocalDate localDate;
    private String textDate;
    private String password;

    public Post(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.userName = requestDto.getUserName();
        this.content = requestDto.getContent();
        this.password = requestDto.getPassword();

        this.localDate = LocalDate.now();  // 현재 시간

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        this.textDate = localDate.format(formatter);
    }

    public void update(PostRequestDto postRequestDto) {
        // 제목, 작성자명, 작성 내용을 수정
        this.title = postRequestDto.getTitle();
        this.userName = postRequestDto.getUserName();
        this.content = postRequestDto.getContent();
    }


}
