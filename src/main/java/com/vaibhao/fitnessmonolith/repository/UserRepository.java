package com.vaibhao.fitnessmonolith.repository;

import com.vaibhao.fitnessmonolith.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, String> {
    UserModel findByEmail(String email);
}
