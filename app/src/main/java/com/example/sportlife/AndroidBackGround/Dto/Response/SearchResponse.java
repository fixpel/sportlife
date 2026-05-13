package com.example.sportlife.AndroidBackGround.Dto.Response;

import androidx.core.text.util.LocalePreferences;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchResponse {
    private List<Exercise> exercises;
    private int size;
    private int page;
    private int totalPage;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Exercise{
        private String video;
        private String description;
        private String name;
        private String photo;
        private List<String> muscles;
        private List<String> items;
        private Boolean favourites;
        private String experts;
    }
}
