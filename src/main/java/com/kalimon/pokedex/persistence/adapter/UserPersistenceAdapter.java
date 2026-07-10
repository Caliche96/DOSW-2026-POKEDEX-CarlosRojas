package com.kalimon.pokedex.persistence.adapter;

import com.kalimon.pokedex.core.model.User;
import com.kalimon.pokedex.core.port.UserPersistencePort;
import com.kalimon.pokedex.persistence.mapper.UserPersistenceMapper;
import com.kalimon.pokedex.persistence.repository.relational.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserPersistencePort {

    private final UserJpaRepository repository;
    private final UserPersistenceMapper mapper;

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email).map(mapper::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public User save(User user) {
        return mapper.toDomain(repository.save(mapper.toEntity(user)));
    }
}