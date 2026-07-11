package com.kalimon.pokedex.core.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class User {
    Long id;
    String email;
    String password;
    String username;
    String role;
    Boolean active;
}