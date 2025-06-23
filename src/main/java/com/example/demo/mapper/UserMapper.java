package com.example.demo.mapper;


import com.example.demo.entity.UserEntity;
import com.example.demo.util.PasswordHasher;
import com.example.model.UserCreateRequest;
import com.example.model.UserResponse;
import com.example.model.UserUpdateRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", source = "password", qualifiedByName = "hashPassword")
    UserEntity requestMapToEntity(UserCreateRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", source = "password", qualifiedByName = "hashPassword")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget UserEntity entity, UserUpdateRequest request);

    UserResponse entityMapToResponse(UserEntity entity);
    List<UserResponse> entityMapToResponseList(List<UserEntity> entities);

    @Named("hashPassword")
    default String hashPassword(String rawPassword) {
        return PasswordHasher.hashPassword(rawPassword);
    }
}
