package com.example.hanghaeblog.Service;

import com.example.hanghaeblog.dto.PostRequestDto;
import com.example.hanghaeblog.dto.PostResponseDto;
import com.example.hanghaeblog.entity.Post;
import com.example.hanghaeblog.repository.PostRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

public class PostService {

    private final Map<Long, Post> postList = new HashMap<>();
    private final JdbcTemplate jdbcTemplate;

    public PostService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 게시글 작성 API
    //    - 제목, 작성자명, 비밀번호, 작성 내용을 저장하고
    //    - 저장된 게시글을 Client 로 반환하기
    public PostResponseDto createPost(PostRequestDto requestDto){
        // RequestDto -> Entity
        Post post = new Post(requestDto);

        //DB 저장
        PostRepository postRepository = new PostRepository(jdbcTemplate);
        Post savePost = postRepository.save(post);

        // Entity -> ResponseDto
        PostResponseDto PostResponseDto = new PostResponseDto(savePost);

            return PostResponseDto;
    }

    // 전체 게시글 목록 조회 API
    //- 제목, 작성자명, 작성 내용, 작성 날짜를 조회하기
    //- 작성 날짜 기준 내림차순으로 정렬하기
    public List<PostResponseDto> getPosts() {
        // DB 조회
        PostRepository postRepository = new PostRepository(jdbcTemplate);
        return postRepository.findAll();
    }

    // 선택한 게시글 조회 API
    //    - 선택한 게시글의 제목, 작성자명, 작성 날짜, 작성 내용을 조회하기
    //    (검색 기능이 아닙니다. 간단한 게시글 조회만 구현해주세요.)
    public PostResponseDto getPost(Long id) {
        PostResponseDto response = new PostResponseDto(postList.get(id));
        return response;
    }


    // 선택한 게시글 수정 API
    //    - 수정을 요청할 때 수정할 데이터와 비밀번호를 같이 보내서 서버에서 비밀번호 일치 여부를 확인 한 후
    //    - 제목, 작성자명, 작성 내용을 수정하고 수정된 게시글을 Client 로 반환하기
    public PostResponseDto updatePost(Long id, String password, PostRequestDto requestDto) {
        PostRepository postRepository = new PostRepository(jdbcTemplate);

        // 해당 메모가 DB에 존재하는지 확인
        Post post1 = postRepository.findById(id);

        if (postList.containsKey(id) && postList.get(id).getPassword().equals(requestDto.getPassword())) {
            // 해당 메모 가져오기
            Post post = postList.get(id);

            // Post 수정
            postRepository.update(id, password, requestDto);

            PostResponseDto responseDto = new PostResponseDto(post);
            return responseDto;
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }

    // 선택한 게시글 삭제 API
    //    - 삭제를 요청할 때 비밀번호를 같이 보내서 서버에서 비밀번호 일치 여부를 확인 한 후
    //    - 선택한 게시글을 삭제하고 Client 로 성공했다는 표시 반환하기
    public String deletePost(Long id, String password) {
        PostRepository postRepository = new PostRepository(jdbcTemplate);

        // 해당 메모가 DB에 존재하는지 확인
        if(postList.containsKey(id) && postList.get(id).getPassword().equals(password) ) {
            // 해당 메모 삭제하기
            postRepository.delete(id);


            return "삭제 성공했습니다.";
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }
}
