package com.vaibhao.fitnessmonolith.service;

import com.vaibhao.fitnessmonolith.DTO.Request.LoginRequestDTO;
import com.vaibhao.fitnessmonolith.DTO.Request.UserRequestDTO;
import com.vaibhao.fitnessmonolith.DTO.Response.UserResponseDTO;
import com.vaibhao.fitnessmonolith.enums.UserRole;
import com.vaibhao.fitnessmonolith.model.UserModel;
import com.vaibhao.fitnessmonolith.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDTO register(UserRequestDTO userRequestDTO) {

        UserRole role = userRequestDTO.getRole()!=null?userRequestDTO.getRole(): UserRole.USER;

        UserModel userModel = UserModel.builder()
                .email(userRequestDTO.getEmail())
                .password(passwordEncoder.encode(userRequestDTO.getPassword()))
                .firstName(userRequestDTO.getFirstName())
                .lastName(userRequestDTO.getLastName())
                .role(role)
                .build();

        UserModel saveUser = userRepository.save(userModel);

        return mapToResponseDTO(saveUser);
    }

    public UserResponseDTO mapToResponseDTO(UserModel saveUser) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(saveUser.getId());
        userResponseDTO.setEmail(saveUser.getEmail());
        userResponseDTO.setPassword(saveUser.getPassword());
        userResponseDTO.setFirstName(saveUser.getFirstName());
        userResponseDTO.setLastName(saveUser.getLastName());
        userResponseDTO.setRole(saveUser.getRole());
        userResponseDTO.setCreatedAt(saveUser.getCreatedAt());
        userResponseDTO.setUpdatedAt(saveUser.getUpdatedAt());

        return userResponseDTO;
    }

    public UserModel authenticate(LoginRequestDTO loginRequestDTO) {
        UserModel userModel = userRepository.findByEmail(loginRequestDTO.getEmail());
        if (userModel == null) {
            throw new BadCredentialsException("Invalid email or password");
        }

        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), userModel.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }
        return  userModel;
    }
}
