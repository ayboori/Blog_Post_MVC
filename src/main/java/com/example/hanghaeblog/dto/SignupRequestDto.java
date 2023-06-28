package com.example.hanghaeblog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    // 최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)로 구성
    @NotBlank
    private String username;
    // 최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9)
//    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotBlank
    private String password;
//    private boolean admin = false;
    private String adminToken = "";
}