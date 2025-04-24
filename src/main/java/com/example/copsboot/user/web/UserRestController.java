package com.example.copsboot.user.web;

import com.example.copsboot.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    @GetMapping("/me")
    public Map<String, Object> myself(@AuthenticationPrincipal Jwt jwt) {
        return Map.of("subject", jwt.getSubject(),
                "claims", jwt.getClaims());
    }
}

@GetMapping("/me")
public Map<String, Object> myself(@AuthenticationPrincipal Jwt jwt) {
    Optional<User> userByAuthServerId = userService.findUserByAuthServerId(new
            AuthServerId(UUID.fromString(jwt.getSubject())));
    Map<String, Object> result = new HashMap<>();
    userByAuthServerId.ifPresent(user -> result.put("userId", user.getId().
            asString()));
    result.put("subject", jwt.getSubject());
    result.put("claims", jwt.getClaims());
    return result;
}

@PostMapping
@ResponseStatus(HttpStatus.CREATED)
public UserDto createUser(@AuthenticationPrincipal Jwt jwt,
                          @RequestBody CreateUserRequest request) {
    CreateUserParameters parameters = request.toParameters(jwt);
    User user = userService.createUser(parameters);
    return UserDto.fromUser(user);
}

