package com.softarex.QuestionsPortal.mapper;

import com.softarex.QuestionsPortal.dto.UserDto;
import com.softarex.QuestionsPortal.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper MAPPER = Mappers.getMapper(UserMapper.class); // надо ли
    UserDto userToDto(User user);
    User dtoToUser(UserDto userDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User updateUser(User source, @MappingTarget User target);

}
