package com.bash.Event.ticketing.Services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EventsService {

    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello world");
    }
}
