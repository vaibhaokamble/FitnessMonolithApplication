package com.vaibhao.fitnessmonolith.security;

import com.vaibhao.fitnessmonolith.model.UserModel;
import com.vaibhao.fitnessmonolith.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserModel userModel = userRepository.findByEmail(email);

        if(userModel==null){
            throw new UsernameNotFoundException("User not found: " +email);
        }
        return User.builder()
                .username(userModel.getEmail())
                .password(userModel.getPassword())
                .roles(userModel.getRole().name())
                .build();
    }

}
