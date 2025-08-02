package com.bash.Event.ticketing.Controllers;

import com.bash.Event.ticketing.DTO.UserRequest;
import com.bash.Event.ticketing.Models.User;
import com.bash.Event.ticketing.Repositories.UserRepository;
import com.bash.Event.ticketing.Services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<List<User>> fetchUsers(){
        return userService.fetchUsers();
    }

    @PutMapping("/update")
    public ResponseEntity<String > update(@RequestBody UserRequest userRequest){
        return  userService.update(userRequest);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String > delete(@PathVariable Long id){
        return userService.delete(id);
    }
}
