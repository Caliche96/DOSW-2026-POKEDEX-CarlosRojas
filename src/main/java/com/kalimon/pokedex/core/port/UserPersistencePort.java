package com.kalimon.pokedex.core.port;

import com.kalimon.pokedex.core.model.User;
import java.util.Optional;

public interface UserPersistencePort {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    User save(User user);
}