package com.softarex.QuestionsPortal.repository;

import com.softarex.QuestionsPortal.entity.User;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User,UUID> {

    User findUserByEmail(String email);
}
