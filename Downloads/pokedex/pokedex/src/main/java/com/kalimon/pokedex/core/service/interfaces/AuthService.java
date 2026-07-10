package com.kalimon.pokedex.core.service.interfaces;

import com.kalimon.pokedex.core.model.User;

public interface AuthService {
    String login(String email, String password);
    User register(User user);
}