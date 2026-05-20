package com.example.sportlife.AndroidBackGround.Dto.Response;

import java.util.List;

import lombok.Data;

@Data
public class FindAvatarsResponse {
        private List<String> names;
        private int page;
        private int size;
        private long totalPage;
}
