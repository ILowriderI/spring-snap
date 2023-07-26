package com.example.myproject.controllers;

import com.example.myproject.dto.AuthenticationRequestDTO;
import com.example.myproject.dto.AuthenticationResponseDTO;
import com.example.myproject.dto.RegisterRequestDTO;
import com.example.myproject.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor

public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDTO> register(
            @Valid @RequestBody RegisterRequestDTO request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDTO> authenticate(
            @RequestBody AuthenticationRequestDTO request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<Boolean> isUserPresent(@PathVariable String email) {

        return ResponseEntity.ok(service.isUserPresent(email));
    }

}
