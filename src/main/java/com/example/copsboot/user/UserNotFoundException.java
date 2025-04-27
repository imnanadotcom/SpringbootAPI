package com.example.copsboot.user;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(UserId userId) {
        super(String.format("Unable to find user with id %s", userId));
    }

    public UserNotFoundException(AuthServerId authServerId) {
        super(String.format("Unable to find auth server with id %s", authServerId));

    }
}
