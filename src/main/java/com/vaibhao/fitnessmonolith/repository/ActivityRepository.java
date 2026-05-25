package com.vaibhao.fitnessmonolith.repository;

import com.vaibhao.fitnessmonolith.model.ActivityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<ActivityModel, String> {
    List<ActivityModel> findByUserId(String userId);
}
