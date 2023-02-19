package com.softarex.QuestionsPortal.service;


import com.softarex.QuestionsPortal.dto.UserDto;
import com.softarex.QuestionsPortal.dto.UserDtoWithPassword;
import com.softarex.QuestionsPortal.entity.User;
import com.softarex.QuestionsPortal.exception.IncorrectPasswordException;
import com.softarex.QuestionsPortal.mapper.UserMapper;
import com.softarex.QuestionsPortal.mapper.UserWithPasswordMapper;
import com.softarex.QuestionsPortal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserWithPasswordMapper userWithPasswordMapper;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public User addUser(UserDtoWithPassword userDto) {
        if (userDto.getHelperPassword().equals(userDto.getPassword())) {  //добавить хеширование пароля
            User user = userWithPasswordMapper.dtoToUser(userDto);
            return userRepository.save(user);
        }else {throw new RuntimeException();} //кидать свой эксепшон
    }

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(); //кидать свой эксепшон
    }

    public boolean deleteUser(String password) {
        String email = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = getUserByEmail(email);
        if (user.getPassword().equals(password)) {
            userRepository.delete(user); //делать пометку о том что юзер не активен
            return true;
        }
        return false;
    }

    public User updateUser(UserDtoWithPassword userDtoUpdate) throws IncorrectPasswordException {
        String email = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User userBeforeChanges = getUserByEmail(email);
        passwordModificationLogic(userDtoUpdate, userBeforeChanges);
        User userChanges = userWithPasswordMapper.dtoToUser(userDtoUpdate); //проверка на то существует ли уже пользовательс таким имейлом
        User userAfterChanges = userWithPasswordMapper.updateUser(userChanges, userBeforeChanges);
        return userRepository.save(userAfterChanges);
    }

    private void passwordModificationLogic(UserDtoWithPassword userDtoUpdate, User userBeforeChanges) throws IncorrectPasswordException {//think about naming
        if (!userDtoUpdate.getPassword().equals(userDtoUpdate.getHelperPassword())) {
            if (userBeforeChanges.getPassword().equals(userDtoUpdate.getPassword())) {
                userDtoUpdate.setPassword(userDtoUpdate.getHelperPassword());
            } else {
                throw new IncorrectPasswordException(userDtoUpdate);
            }
        }
    }

    public UserDto getUserDtoOfAuthenticatedUser() {
        return userMapper.userToDto(getAuthenticatedUser());
    }

    public UserDtoWithPassword getUserDtoWithPasswordOfAuthenticatedUser() {
        return userWithPasswordMapper.userToDto(getAuthenticatedUser());
    }

    public User getAuthenticatedUser() {
        String email = ((org.springframework.security.core.userdetails.User) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal()).getUsername();
        return getUserByEmail(email);
    }

}
