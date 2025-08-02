package com.bash.Event.ticketing.Controllers;

import com.bash.Event.ticketing.DTO.SignInRequest;
import com.bash.Event.ticketing.DTO.UserRequest;
import com.bash.Event.ticketing.Models.User;
import com.bash.Event.ticketing.Services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    public final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequest user){
        return authenticationService.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<SignInRequest> login(@RequestBody UserRequest request){
        return authenticationService.login(request);
    }
}
