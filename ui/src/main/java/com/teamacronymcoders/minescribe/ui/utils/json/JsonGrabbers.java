package com.teamacronymcoders.minescribe.ui.utils.json;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Optional;
import java.util.function.Function;

public class JsonGrabbers {
    public static <T> T getObjectOrThrow(JsonNode jsonNode, String name, Function<JsonNode, T> function) {
        return Optional.ofNullable(jsonNode.get(name))
                .map(function)
                .orElseThrow(() -> new IllegalStateException("Couldn't find " + name));
    }

    public static <T> Optional<T> tryGetObject(JsonNode jsonNode, String name, Function<JsonNode, T> function) {
        return Optional.ofNullable(jsonNode.get(name))
                .map(function);
    }

    public static <T> T getObjectOrNull(JsonNode jsonNode, String name, Function<JsonNode, T> function) {
        return tryGetObject(jsonNode, name, function)
                .orElse(null);
    }
}
