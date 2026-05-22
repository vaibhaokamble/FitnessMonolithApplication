package com.vaibhao.fitnessmonolith.service;

import com.vaibhao.fitnessmonolith.DTO.Request.ActivityRequestDTO;
import com.vaibhao.fitnessmonolith.DTO.Response.ActivityResponseDTO;
import com.vaibhao.fitnessmonolith.model.ActivityModel;
import com.vaibhao.fitnessmonolith.model.UserModel;
import com.vaibhao.fitnessmonolith.repository.ActivityRepository;
import com.vaibhao.fitnessmonolith.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;


    public ActivityResponseDTO tractActivity(ActivityRequestDTO activityRequestDTO) {

        UserModel userModel = userRepository.findById(activityRequestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Invalid User" + activityRequestDTO.getUserId() + "User not found"));

        ActivityModel activityModel = ActivityModel.builder()
                .user(userModel)
                .activityType(activityRequestDTO.getActivityType())
                .duration(activityRequestDTO.getDuration())
                .calories(activityRequestDTO.getCalories())
                .additionalMatrics(activityRequestDTO.getAdditionalMatrics())
                .weight(activityRequestDTO.getWeight())
                .build();
        ActivityModel savedActivity = activityRepository.save(activityModel);

        return mapToResponse(savedActivity);
    }

    private ActivityResponseDTO mapToResponse(ActivityModel activityModel) {
        ActivityResponseDTO activityResponseDTO = new ActivityResponseDTO();
        activityResponseDTO.setId(activityModel.getId());
        activityResponseDTO.setUserId(activityModel.getUser().getId());
        activityResponseDTO.setActivityType(activityModel.getActivityType());
        activityResponseDTO.setDuration(activityModel.getDuration());
        activityResponseDTO.setCalories(activityModel.getCalories());
        activityResponseDTO.setAdditionalMatrics(activityModel.getAdditionalMatrics());
        activityResponseDTO.setWeight(activityModel.getWeight());
        activityResponseDTO.setCreatedAt(activityModel.getCreatedAt());
        activityResponseDTO.setUpdatedAt(activityModel.getUpdatedAt());
        return activityResponseDTO;
    }

    public List<ActivityResponseDTO> getUserActivities(String userId) {
        List<ActivityModel> activityModelList = activityRepository.findByUserId(userId);

        return activityModelList.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
}
