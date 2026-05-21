package com.safoev.NeoSlots.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record MasterRequest(
        @NotNull UUID userId,
        @NotNull UUID companyId,
        String bio
) {}
