package com.softarex.QuestionsPortal.mapper;

import com.softarex.QuestionsPortal.dto.UserDto;
import com.softarex.QuestionsPortal.entity.User;
import com.softarex.QuestionsPortal.service.UserService;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public interface  UserMapper {

    UserDto userToDto(User user);
    User dtoToUser(UserDto userDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User updateUser(User source, @MappingTarget User target);

}
