package com.softarex.QuestionsPortal.security;

import com.softarex.QuestionsPortal.entity.User;
import com.softarex.QuestionsPortal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findActiveUserByEmail(email);
        if(user ==null) {
            throw new UsernameNotFoundException("User Not Found");
        }
           return new org.springframework.security
                .core.userdetails.User(user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("USER")));
    }


}