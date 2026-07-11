package com.kalimon.pokedex.controller.mapper;

import com.kalimon.pokedex.controller.dto.request.RegisterRequest;
import com.kalimon.pokedex.core.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", constant = "TRAINER")
    @Mapping(target = "active", constant = "true")
    User toDomain(RegisterRequest request);
}