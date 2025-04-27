package com.example.copsboot.user;

import java.util.UUID;

public record AuthServerId(UUID value) {
    public AuthServerId {
        Assert.notNull(value, "The AuthServerId value should not b null");
    }
}
