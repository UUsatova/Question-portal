package com.softarex.QuestionsPortal.service;


import com.softarex.QuestionsPortal.dto.UserDto;
import com.softarex.QuestionsPortal.dto.UserDtoWithPassword;
import com.softarex.QuestionsPortal.entity.User;
import com.softarex.QuestionsPortal.exception.IncorrectPasswordException;
import com.softarex.QuestionsPortal.exception.ItemNotFoundException;
import com.softarex.QuestionsPortal.mapper.UserMapper;
import com.softarex.QuestionsPortal.mapper.UserWithPasswordMapper;
import com.softarex.QuestionsPortal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService extends AppService {
    private final UserWithPasswordMapper userWithPasswordMapper;
    private final UserMapper userMapper;
    private final QuestionService questionService;

    public UserService(UserRepository repository,UserWithPasswordMapper userWithPasswordMapper
            ,UserMapper userMapper,QuestionService questionService){
        super(repository );
        this.userWithPasswordMapper= userWithPasswordMapper;
        this.userMapper=userMapper;
        this.questionService=questionService;
    }

    public User addUser(UserDtoWithPassword userDto) {
        if (userDto.getHelperPassword().equals(userDto.getPassword())) {  //добавить хеширование пароля
            User user = userWithPasswordMapper.dtoToUser(userDto);
            return userRepository.save(user);
        }else {throw new IncorrectPasswordException();} //по идее этот эксепшн никогда не должен выброситься
    }                                                   //так как на уровне валидации этои пресекается
                                                        //но мне кажется что все таки надо делать эту проверку  на случай если сторонние приложения
                                                        //будут использовать мой сервис. Но визуально мне не нравится как это выглядит.





    public boolean softDeleteUser(String password) {
        String email = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = getUserByEmail(email);
        if (user.getPassword().equals(password)) {
            user.setActive(false);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public User updateUser(UserDtoWithPassword userDtoUpdate) throws IncorrectPasswordException {
        String email = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User userBeforeChanges = getUserByEmail(email);
        passwordModificationLogic(userDtoUpdate, userBeforeChanges);
        User userChanges = userWithPasswordMapper.dtoToUser(userDtoUpdate);
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



}
