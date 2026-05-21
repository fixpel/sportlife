package com.example.sportlife.AndroidBackGround.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProfileResponse {
    private String login;
    private String avatar;
    private String experts;
    private Long activity;
    private Long top;
}
