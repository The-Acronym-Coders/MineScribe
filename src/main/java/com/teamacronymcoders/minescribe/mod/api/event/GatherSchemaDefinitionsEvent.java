package com.teamacronymcoders.minescribe.mod.api.event;

import com.google.common.collect.Maps;
import com.teamacronymcoders.minescribe.mod.api.definition.Definition;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.Map;
import java.util.Optional;

public class GatherSchemaDefinitionsEvent extends Event {
    private final Map<String, Map<String, Definition>> definitions;

    public GatherSchemaDefinitionsEvent() {
        definitions = Maps.newHashMap();
    }

    public Map<String, Map<String, Definition>> getDefinitions() {
        return definitions;
    }

    public Map<String, Definition> getDefinitionsFor(String fileName) {
        return definitions.get(fileName);
    }

    public void addDefinition(String name, Definition definition) {
        addDefinition("definitions", name, definition);
    }

    public void addDefinition(String fileName, String name, Definition definition) {
        Optional.ofNullable(definitions.putIfAbsent(fileName, Maps.newHashMap()))
                .ifPresent(map -> map.put(name, definition));
    }
}
