package com.jordan.albano.verticalslice.features.users.findbyid.dto;

import java.util.List;

public record FindUserByIdDto(
        String id,
        String name,
        String lastname,
        List<String> roles,
        String email,
        String username


) {
}
