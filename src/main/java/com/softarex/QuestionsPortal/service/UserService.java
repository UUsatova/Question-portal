package com.softarex.QuestionsPortal.service;


import com.softarex.QuestionsPortal.dto.UserDto;
import com.softarex.QuestionsPortal.dto.UserDtoWithPassword;
import com.softarex.QuestionsPortal.entity.User;
import com.softarex.QuestionsPortal.mapper.UserMapper;
import com.softarex.QuestionsPortal.mapper.UserWithPasswordMapper;
import com.softarex.QuestionsPortal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserWithPasswordMapper userWithPasswordMapper;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public User addUser(UserDtoWithPassword userDto) {
        User user = userWithPasswordMapper.dtoToUser(userDto); //добавить хеширование пароля
        return userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public boolean deleteUser(String password) {
        String email = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = getUserByEmail(email);
        if (user.getPassword().equals(password)) {
            userRepository.delete(user); //каскадное удаление вопросов ответов и прочей лабуды
            return true;
        }
        return false;
    }

    public User updateUser(UserDtoWithPassword userDtoUpdate) {
        String email = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User userBeforeChanges = getUserByEmail(email);
        User userChanges = userWithPasswordMapper.dtoToUser(userDtoUpdate);
        User userAfterChanges = userRepository.save(userWithPasswordMapper.updateUser(userChanges, userBeforeChanges));
        return userAfterChanges;
    }

    public UserDto getUserDtoOfAuthenticatedUser() {
        return userMapper.userToDto(getAuthenticatedUser());
    }

    public UserDtoWithPassword getUserDtoWithPasswordOfAuthenticatedUser() {
        return userWithPasswordMapper.userToDto(getAuthenticatedUser());
    }

    private User getAuthenticatedUser() {
        String email = ((org.springframework.security.core.userdetails.User) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal()).getUsername();
        return getUserByEmail(email);
    }

}
