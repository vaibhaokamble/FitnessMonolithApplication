package com.vaibhao.fitnessmonolith.repository;

import com.vaibhao.fitnessmonolith.model.RecommendationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendationRepository extends JpaRepository<RecommendationModel, String> {
    List<RecommendationModel> findByUserId(String userId);

    List<RecommendationModel> findByActivityId(String activityId);
}
