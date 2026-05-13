package com.example.sportlife.AndroidBackGround.Dto.Response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindTopResponse {
    private List<EmployeeTop> top;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class EmployeeTop {
        private String login;
        private Long activity;
        private String experts;
        private String avatar;
    }
}
