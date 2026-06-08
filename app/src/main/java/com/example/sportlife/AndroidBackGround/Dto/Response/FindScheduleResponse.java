package com.example.sportlife.AndroidBackGround.Dto.Response;

import java.time.LocalTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindScheduleResponse {
    private List<LocalTime> monday;
    private List<LocalTime> tuesday;
    private List<LocalTime> wednesday;
    private List<LocalTime> thursday;
    private List<LocalTime> friday;
    private List<LocalTime> saturday;
    private List<LocalTime> sunday;
}
