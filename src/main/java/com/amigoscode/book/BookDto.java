package com.amigoscode.book;

import java.time.ZonedDateTime;

public record BookDto(
        Long id,
        String title,
        ZonedDateTime createdAt
) {
}
