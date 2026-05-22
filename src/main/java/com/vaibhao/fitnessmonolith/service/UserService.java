package com.vaibhao.fitnessmonolith.service;

import com.vaibhao.fitnessmonolith.DTO.Request.UserRequestDTO;
import com.vaibhao.fitnessmonolith.DTO.Response.UserResponseDTO;
import com.vaibhao.fitnessmonolith.model.UserModel;
import com.vaibhao.fitnessmonolith.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDTO register(UserRequestDTO userRequestDTO) {
        UserModel userModel = UserModel.builder()
                .email(userRequestDTO.getEmail())
                .password(userRequestDTO.getPassword())
                .firstName(userRequestDTO.getFirstName())
                .lastName(userRequestDTO.getLastName())
                .build();

        UserModel saveUser = userRepository.save(userModel);

        return mapToResponseDTO(saveUser);
    }

    private UserResponseDTO mapToResponseDTO(UserModel saveUser) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(saveUser.getId());
        userResponseDTO.setEmail(saveUser.getEmail());
        userResponseDTO.setPassword(saveUser.getPassword());
        userResponseDTO.setFirstName(saveUser.getFirstName());
        userResponseDTO.setLastName(saveUser.getLastName());
        userResponseDTO.setCreatedAt(saveUser.getCreatedAt());
        userResponseDTO.setUpdatedAt(saveUser.getUpdatedAt());

        return userResponseDTO;
    }
}
