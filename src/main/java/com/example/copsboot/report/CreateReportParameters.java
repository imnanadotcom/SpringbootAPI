package com.example.copsboot.report;

import com.example.copsboot.user.UserId;

import java.time.Instant;

public interface CreateReportParameters (UserId userId, Instant dateTime, String description) {
}
