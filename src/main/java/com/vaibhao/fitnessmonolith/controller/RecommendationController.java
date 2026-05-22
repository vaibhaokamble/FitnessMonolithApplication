package com.vaibhao.fitnessmonolith.controller;

import com.vaibhao.fitnessmonolith.DTO.Request.RecommendationRequestDTO;
import com.vaibhao.fitnessmonolith.model.RecommendationModel;
import com.vaibhao.fitnessmonolith.repository.RecommendationRepository;
import com.vaibhao.fitnessmonolith.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendation")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    @PostMapping("/generate")
    public ResponseEntity<RecommendationModel> generateRecommendation(@RequestBody RecommendationRequestDTO recommendationRequestDTO){
       RecommendationModel recommendationModel = recommendationService.generateRecommendation(recommendationRequestDTO);
       return ResponseEntity.ok(recommendationModel);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RecommendationModel>> getUserRecommendation(@PathVariable String userId){
        List<RecommendationModel> recommendationModel = recommendationService.getUserRecommendation(userId);
        return ResponseEntity.ok(recommendationModel);
    }

    @GetMapping("/activity/{activityId}")
    public ResponseEntity<List<RecommendationModel>> getActivityRecommendation(@PathVariable String activityId){
        List<RecommendationModel> recommendationModel = recommendationService.getActivityRecommendation(activityId);
        return ResponseEntity.ok(recommendationModel);
    }
}