package com.kalimon.pokedex.core.service.impl;

import com.kalimon.pokedex.core.exception.DuplicateResourceException;
import com.kalimon.pokedex.core.exception.ResourceNotFoundException;
import com.kalimon.pokedex.core.model.User;
import com.kalimon.pokedex.core.port.UserPersistencePort;
import com.kalimon.pokedex.core.service.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserPersistencePort userPort;

    @Override
    public String login(String email, String password) {
        log.debug("Login para: {}", email);
        userPort.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
        return "token";
    }

    @Override
    public User register(User user) {
        log.info("Registrando usuario: {}", user.getEmail());
        if (userPort.existsByEmail(user.getEmail())) {
            throw new DuplicateResourceException("User", "email", user.getEmail());
        }
        return userPort.save(user);
    }
}