package com.vaibhao.fitnessmonolith.service;

import com.vaibhao.fitnessmonolith.DTO.Request.RecommendationRequestDTO;
import com.vaibhao.fitnessmonolith.model.ActivityModel;
import com.vaibhao.fitnessmonolith.model.RecommendationModel;
import com.vaibhao.fitnessmonolith.model.UserModel;
import com.vaibhao.fitnessmonolith.repository.ActivityRepository;
import com.vaibhao.fitnessmonolith.repository.RecommendationRepository;
import com.vaibhao.fitnessmonolith.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final UserRepository userRepository;
    private  final ActivityRepository activityRepository;
    private final RecommendationRepository recommendationRepository;

    public RecommendationModel generateRecommendation(RecommendationRequestDTO recommendationRequestDTO) {
        UserModel userModel = userRepository.findById(recommendationRequestDTO.getUserId())
                .orElseThrow(()-> new RuntimeException("User Not Found" + recommendationRequestDTO.getUserId()));

        ActivityModel activityModel = null;
        if (recommendationRequestDTO.getActivityId() != null && !recommendationRequestDTO.getActivityId().isEmpty()) {
            activityModel = activityRepository.findById(recommendationRequestDTO.getActivityId())
                    .orElseThrow(() -> new RuntimeException("Activity Not Found" + recommendationRequestDTO.getActivityId()));
        }

        RecommendationModel recommendationModel = RecommendationModel.builder()
                .user(userModel)
                .activity(activityModel)
                .type(recommendationRequestDTO.getType())
                .recommendations(recommendationRequestDTO.getRecommendations())
                .improvements(recommendationRequestDTO.getImprovements())
                .suggestions(recommendationRequestDTO.getSuggestions())
                .safety(recommendationRequestDTO.getSafety())
                .build();

        return recommendationRepository.save(recommendationModel);
    }

    public List<RecommendationModel> getUserRecommendation(String userId) {
        return recommendationRepository.findByUserId(userId);
    }

    public List<RecommendationModel> getActivityRecommendation(String activityId) {
        return recommendationRepository.findByActivityId(activityId);
    }
}
