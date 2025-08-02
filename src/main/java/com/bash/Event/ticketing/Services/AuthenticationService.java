package com.bash.Event.ticketing.Services;

import com.bash.Event.ticketing.DTO.SignInRequest;
import com.bash.Event.ticketing.DTO.UserRequest;
import com.bash.Event.ticketing.Models.Role;
import com.bash.Event.ticketing.Models.User;
import com.bash.Event.ticketing.Repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(UserRepository userRepository, TokenService tokenService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<String> register(UserRequest request) {
        System.out.print(request);
        Role role = Role.OWNER;

        if ("ADMIN".equals(request.role())) {
            role = Role.ADMIN;
        }
        if ("BUYER".equals(request.role())) {
            role = Role.BUYER;
        }
        User user = new User(request.username(), passwordEncoder.encode(request.password()), role);
        if (request.email() != null){
            user.setEmail(request.email());
        }
        if (request.fullName() != null){
            user.setFullName(request.fullName());
        }
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    public ResponseEntity<SignInRequest> login (UserRequest request){
        if (request.username() == null || request.password() == null) {
            throw new BadCredentialsException("Enter username and password");
        }

        System.out.print("LOl");
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken
                        (request.username(), request.password()));
        User user = (User) authentication.getPrincipal();
        String roleS = user.getRole().toString();
        String jwt = tokenService.generate(user);
        return ResponseEntity.ok(new SignInRequest(jwt, roleS));
    }

}
