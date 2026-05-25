package com.vaibhao.fitnessmonolith.controller;

import com.vaibhao.fitnessmonolith.DTO.Request.LoginRequestDTO;
import com.vaibhao.fitnessmonolith.DTO.Request.UserRequestDTO;
import com.vaibhao.fitnessmonolith.DTO.Response.LoginResponseDTO;
import com.vaibhao.fitnessmonolith.DTO.Response.UserResponseDTO;
import com.vaibhao.fitnessmonolith.model.UserModel;
import com.vaibhao.fitnessmonolith.security.JwtUtils;
import com.vaibhao.fitnessmonolith.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    private final JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity <UserResponseDTO> register(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.ok(userService.register(userRequestDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            UserModel userModel= userService.authenticate(loginRequestDTO);

            String token = jwtUtils.generateToken(userModel.getId(), userModel.getRole().name());

            return ResponseEntity.ok(new LoginResponseDTO(token,userService.mapToResponseDTO(userModel)));

        } catch (AuthenticationException e) {

            e.printStackTrace();

            return  ResponseEntity.status(401).build();
        }
    }
}
