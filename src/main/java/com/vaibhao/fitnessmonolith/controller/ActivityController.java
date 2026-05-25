package com.vaibhao.fitnessmonolith.controller;

import com.vaibhao.fitnessmonolith.DTO.Request.ActivityRequestDTO;
import com.vaibhao.fitnessmonolith.DTO.Response.ActivityResponseDTO;
import com.vaibhao.fitnessmonolith.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activity")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityResponseDTO> tractActivity(@RequestBody ActivityRequestDTO activityRequestDTO) {
        return ResponseEntity.ok(activityService.tractActivity(activityRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<ActivityResponseDTO>> getUserActivities(@RequestHeader(value = "X-User-ID") String userId) {
        return ResponseEntity.ok(activityService.getUserActivities(userId));
    }
}
