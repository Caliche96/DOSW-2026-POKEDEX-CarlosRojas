package com.kalimon.pokedex.persistence.mapper;

import com.kalimon.pokedex.core.model.User;
import com.kalimon.pokedex.persistence.entity.relational.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {

    User toDomain(UserEntity entity);

    @Mapping(target = "createdAt", ignore = true)
    UserEntity toEntity(User user);
}