package com.softarex.QuestionsPortal.service;

import com.softarex.QuestionsPortal.entity.User;
import com.softarex.QuestionsPortal.exception.ItemNotFoundException;
import com.softarex.QuestionsPortal.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AppService {

    protected final UserRepository userRepository;

    public User getUserByEmail(String email) {
        return userRepository.findActiveUserByEmail(email);
    }
    public User getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(()->new ItemNotFoundException()); //кидать свой эксепшон
    }

    public User getAuthenticatedUser() {
        String email = ((org.springframework.security.core.userdetails.User) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal()).getUsername();
        return getUserByEmail(email);

    }
}
