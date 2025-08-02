package com.bash.Event.ticketing.Services;

import com.bash.Event.ticketing.DTO.UserRequest;
import com.bash.Event.ticketing.Models.User;
import com.bash.Event.ticketing.Repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    public final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public ResponseEntity<String> update(UserRequest userRequest) {
        return null;
    }

    public ResponseEntity<String> delete(Long id) {
        userRepository.delete(userRepository.findById(id).orElseThrow());
        return ResponseEntity.ok("User deleted successfully");
    }

    public ResponseEntity<List<User> > fetchUsers() {
        return ResponseEntity.ok(userRepository.findAll().stream().map(UserRequest::new));
    }
}
