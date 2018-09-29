package com.teamacronymcoders.minescribe.mod.eventhandler;

import com.teamacronymcoders.minescribe.mod.MineScribe;
import com.teamacronymcoders.minescribe.mod.api.definition.StringArrayDefinition;
import com.teamacronymcoders.minescribe.mod.api.event.GatherSchemaDefinitionsEvent;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber(modid = MineScribe.MOD_ID)
public class RegistryDefinitionEventHandler {
    @SubscribeEvent
    public static void gatherDefinitionsEventHandler(GatherSchemaDefinitionsEvent gatherSchemaDefinitionsEvent) {
        setupForgeRegistryNameDefinitions(gatherSchemaDefinitionsEvent);
    }

    private static void setupForgeRegistryNameDefinitions(GatherSchemaDefinitionsEvent gatherSchemaDefinitionsEvent) {
        handleForgeRegistryName(gatherSchemaDefinitionsEvent, "item", ForgeRegistries.ITEMS);
        handleForgeRegistryName(gatherSchemaDefinitionsEvent, "entity", ForgeRegistries.ENTITIES);
        handleForgeRegistryName(gatherSchemaDefinitionsEvent, "block", ForgeRegistries.BLOCKS);
        handleForgeRegistryName(gatherSchemaDefinitionsEvent, "biome", ForgeRegistries.BIOMES);
    }

    private static <T extends IForgeRegistryEntry<T>> void handleForgeRegistryName(GatherSchemaDefinitionsEvent event,
                                                                                   String name, IForgeRegistry<T> forgeRegistry) {
        event.addDefinition("registry_objects",name + "_names", new StringArrayDefinition(
                forgeRegistry.getKeys()
                        .stream()
                        .map(ResourceLocation::toString)
                        .sorted(String::compareTo)
                        .toArray(String[]::new)));
    }
}
