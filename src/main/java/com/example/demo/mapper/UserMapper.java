package com.example.demo.mapper;


import com.example.demo.entity.UserEntity;
import com.example.model.UserCreateRequest;
import com.example.model.UserUpdateRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    UserEntity requestMapToEntity(UserCreateRequest request);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget UserEntity entity, UserUpdateRequest request);
}
