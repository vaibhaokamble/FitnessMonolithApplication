package com.vaibhao.fitnessmonolith.controller;

import com.vaibhao.fitnessmonolith.DTO.Request.UserRequestDTO;
import com.vaibhao.fitnessmonolith.DTO.Response.UserResponseDTO;
import com.vaibhao.fitnessmonolith.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity <UserResponseDTO> register(@RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.ok(userService.register(userRequestDTO));
    }
}
