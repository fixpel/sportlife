package com.example.sportlife.AndroidBackGround.Dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateEmployeeRequest {
    private String login;
    private String avatar;
}
