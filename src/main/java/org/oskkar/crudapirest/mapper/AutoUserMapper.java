package org.oskkar.crudapirest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.oskkar.crudapirest.dto.UserDto;
import org.oskkar.crudapirest.entity.User;

@Mapper
public interface AutoUserMapper {
    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);
    UserDto toUserDto(User user);
    User toUser(UserDto userDto);
}
