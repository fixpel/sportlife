package com.example.sportlife.AndroidBackGround.Dto.Request;

import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScheduleRequest {
    private String name;
    private String time;
}
