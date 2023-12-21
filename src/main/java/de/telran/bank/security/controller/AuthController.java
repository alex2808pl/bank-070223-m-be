package de.telran.bank.security.controller;

import de.telran.bank.security.jwt.JwtRequest;
import de.telran.bank.security.jwt.JwtRequestRefresh;
import de.telran.bank.security.jwt.JwtResponse;
import de.telran.bank.security.service.AuthService;
import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    /**
     * The authentication service bean for handling authentication operations.
     */
    private final AuthService authService;

    /**
     * Handles user login and returns a JWT response.
     *
     * @param authRequest the authentication request containing username and password.
     * @return a ResponseEntity containing the JWT response.
     * @throws AuthException if authentication fails.
     */
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest authRequest) throws AuthException {
        final JwtResponse token = authService.login(authRequest);
        return ResponseEntity.ok(token);
    }

    /**
     * Obtains a new access token using a refresh token.
     *
     * @param request the request containing the refresh token.
     * @return a ResponseEntity containing the JWT response.
     * @throws AuthException if token refresh fails.
     */
    @PostMapping("/token")
    public ResponseEntity<JwtResponse> getNewAccessToken(@RequestBody JwtRequestRefresh request) throws AuthException {
        final JwtResponse token = authService.getAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

    /**
     * Obtains a new refresh token using an existing refresh token.
     *
     * @param request the request containing the refresh token.
     * @return a ResponseEntity containing the JWT response.
     * @throws AuthException if token refresh fails.
     */
    @PostMapping("/refresh")
    public ResponseEntity<JwtResponse> getNewRefreshToken(@RequestBody JwtRequestRefresh request) throws AuthException {
        final JwtResponse token = authService.refresh(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

}
