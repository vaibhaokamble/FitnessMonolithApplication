package com.vaibhao.fitnessmonolith.DTO.Response;

import com.vaibhao.fitnessmonolith.enums.ActivityType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityResponseDTO {
    private String id;
    private String userId;
    private ActivityType activityType;
    private Map<String,Object> additionalMatrics;
    private Integer duration;
    private Integer calories;
    private Integer weight;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
