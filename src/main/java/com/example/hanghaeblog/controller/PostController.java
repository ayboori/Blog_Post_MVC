package com.example.hanghaeblog.controller;

import com.example.hanghaeblog.Service.PostService;
import com.example.hanghaeblog.dto.PostRequestDto;
import com.example.hanghaeblog.dto.PostResponseDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PostController {
    private final JdbcTemplate jdbcTemplate;
    public PostController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 게시글 작성 API
    @PostMapping("/posts")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto) {
        PostService postService = new PostService(jdbcTemplate);
        return postService.createPost(requestDto);
    }

    // 전체 게시글 목록 조회 API
    @GetMapping("/posts")
    public List<PostResponseDto> getPosts() {
        PostService postService = new PostService(jdbcTemplate);
        return postService.getPosts();
    }

    // 선택한 게시글 조회 API
    @GetMapping("/posts/{id}")
    @ResponseBody
    public PostResponseDto getPost(@PathVariable Long id) {
        PostService postService = new PostService(jdbcTemplate);
        return postService.getPost(id);
    }

    // 선택한 게시글 수정 API
    @PutMapping("/posts/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestParam String password, @RequestBody PostRequestDto requestDto) {
        PostService postService = new PostService(jdbcTemplate);
        return postService.updatePost(id, password, requestDto);
    }

    // 선택한 게시글 삭제 API
    //    - 삭제를 요청할 때 비밀번호를 같이 보내서 서버에서 비밀번호 일치 여부를 확인 한 후
    //    - 선택한 게시글을 삭제하고 Client 로 성공했다는 표시 반환하기
    @DeleteMapping("/posts/{id}")
    public String deletePost(@PathVariable Long id, @RequestParam String password) {
        PostService postService = new PostService(jdbcTemplate);
        return postService.deletePost(id, password);
    }
}