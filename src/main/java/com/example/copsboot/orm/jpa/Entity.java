package com.example.copsboot.orm.jpa;

public interface Entity<T extends EntityId> {
    T getId();
}
