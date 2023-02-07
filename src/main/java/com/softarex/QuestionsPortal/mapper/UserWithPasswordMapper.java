package com.softarex.QuestionsPortal.mapper;

import com.softarex.QuestionsPortal.dto.UserDtoWithPassword;
import com.softarex.QuestionsPortal.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserWithPasswordMapper {
    UserWithPasswordMapper MAPPER = Mappers.getMapper(UserWithPasswordMapper.class); // надо ли
    UserDtoWithPassword userToDto(User user);
    User dtoToUser(UserDtoWithPassword userDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User updateUser(User source, @MappingTarget User target);
}
