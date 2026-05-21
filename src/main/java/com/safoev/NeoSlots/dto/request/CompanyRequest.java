package com.safoev.NeoSlots.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CompanyRequest(
        @NotBlank @Size(max = 255) String name,
        @NotBlank @Size(max = 255) String slug,
        String address,
        @Size(max = 20) String phone,
        @Size(max = 255) String email,
        @Size(max = 100) String timezone
) {}
