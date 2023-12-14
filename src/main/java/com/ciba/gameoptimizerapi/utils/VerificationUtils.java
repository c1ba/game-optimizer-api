package com.ciba.gameoptimizerapi.utils;

import java.util.List;
import java.util.UUID;

public class VerificationUtils {

    public static List<UUID> getInexistingUUIDsFromRequested(List<UUID> existingUUIDs, List<UUID> requestedUUIDs) {
        return requestedUUIDs.stream()
                .filter(req ->
                    existingUUIDs.stream()
                            .filter(existing -> existing.equals(req))
                            .findAny()
                            .isEmpty()
                ).toList();
    }
}
