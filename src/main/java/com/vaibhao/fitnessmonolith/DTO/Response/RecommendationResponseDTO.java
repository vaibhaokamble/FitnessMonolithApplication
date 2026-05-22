package com.vaibhao.fitnessmonolith.DTO.Response;

import java.util.List;

public class RecommendationResponseDTO {
    private String userId;
    private String activityId;
    private List<String> improvements;
    private List<String> suggestions;
    private  List<String> safety;
}
