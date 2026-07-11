package com.kalimon.pokedex.security;

import com.kalimon.pokedex.core.model.User;
import com.kalimon.pokedex.core.port.UserPersistencePort;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final UserPersistencePort userPersistencePort;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        User user = userPersistencePort.findByEmail(email)
                .orElseGet(() -> userPersistencePort.save(
                        User.builder()
                                .email(email)
                                .username(name)
                                .password("")
                                .role("TRAINER")
                                .active(true)
                                .build()
                ));

        org.springframework.security.core.userdetails.User userDetails =
                new org.springframework.security.core.userdetails.User(
                        user.getEmail(),
                        user.getPassword(),
                        List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
                );

        String token = jwtService.generateToken(userDetails);
        log.info("OAuth2 login exitoso para: {}", email);
        response.sendRedirect("/api/v1/auth/oauth2/success?token=" + token);
    }
}