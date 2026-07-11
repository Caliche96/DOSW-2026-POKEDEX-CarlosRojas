package com.kalimon.pokedex.controller.impl;

import com.kalimon.pokedex.controller.api.TeamApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class TeamController implements TeamApi {

    @Override
    public ResponseEntity<List<Map<String, Object>>> getMyTeams() {
        return ResponseEntity.ok(List.of());
    }

    @Override
    public ResponseEntity<Map<String, Object>> createTeam(Map<String, Object> request) {
        return ResponseEntity.ok(Map.of("message", "Equipo creado"));
    }

    @Override
    public ResponseEntity<Void> deleteTeam(Long id) {
        return ResponseEntity.noContent().build();
    }
}