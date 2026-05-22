package com.vaibhao.fitnessmonolith.DTO.Request;

import com.vaibhao.fitnessmonolith.enums.ActivityType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityRequestDTO {
    private String userId;
    private ActivityType activityType;
    private Map<String,Object> additionalMatrics;
    private Integer duration;
    private Integer calories;
    private Integer weight;
}
