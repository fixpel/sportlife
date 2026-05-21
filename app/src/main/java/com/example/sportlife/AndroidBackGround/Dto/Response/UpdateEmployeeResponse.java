package com.example.sportlife.AndroidBackGround.Dto.Response;

import androidx.media3.common.C;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateEmployeeResponse {
    private String message;
    private String accessToken;
    private String refreshToken;
}
