package com.softarex.QuestionsPortal.repository;

import com.softarex.QuestionsPortal.entity.User;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User,UUID> {

    @Query("SELECT u FROM User u WHERE u.isActive = true AND u.email=:email")
    User findActiveUserByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.isActive = true AND u.id=:id")
    Optional<User> findActiveUserById(UUID id);
    boolean existsByEmail(String email); //и активность предложить восстановить???
}
