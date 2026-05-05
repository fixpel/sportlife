package com.example.sportlife.AndroidBackGround.Dto.Request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchRequest {
    private List<String> muscles;
    private List<String> items;
}
