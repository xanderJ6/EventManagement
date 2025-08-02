package com.bash.Event.ticketing.DTO;

public record UserRequest(String username,
                          String password,
                          String role,
                          String email,
                          String fullName) {


}
