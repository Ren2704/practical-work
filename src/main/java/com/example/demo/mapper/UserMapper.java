package com.example.demo.mapper;


import com.example.demo.dao.entity.UserEntity;
import com.example.model.UserCreateRequest;
import com.example.model.UserResponse;
import com.example.model.UserUpdateRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = UserMapperHelper.class)
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", source = "password", qualifiedByName = "encodePassword")
    UserEntity toEntity(UserCreateRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", source = "password", qualifiedByName = "encodePassword")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget UserEntity entity, UserUpdateRequest request);

    UserResponse toResponse(UserEntity entity);
}
