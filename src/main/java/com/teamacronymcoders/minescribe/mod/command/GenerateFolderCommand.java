package com.teamacronymcoders.minescribe.mod.command;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.teamacronymcoders.base.util.files.BaseFileUtils;
import com.teamacronymcoders.minescribe.mod.MineScribe;
import com.teamacronymcoders.minescribe.mod.api.definition.Definition;
import com.teamacronymcoders.minescribe.mod.api.definition.Definitions;
import com.teamacronymcoders.minescribe.mod.api.event.GatherSchemaDefinitionsEvent;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import org.apache.commons.io.FilenameUtils;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

public class GenerateFolderCommand extends CommandBase {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    @Nonnull
    public String getName() {
        return "generate";
    }

    @Override
    @Nonnull
    public String getUsage(@Nonnull ICommandSender sender) {
        return "Generates all the Folders and Files that Minescribe UI needs";
    }

    @Override
    @ParametersAreNonnullByDefault
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        GatherSchemaDefinitionsEvent gatherSchemaDefinitionsEvent = new GatherSchemaDefinitionsEvent();
        MinecraftForge.EVENT_BUS.post(gatherSchemaDefinitionsEvent);

        gatherSchemaDefinitionsEvent.getDefinitions().entrySet().stream()
                .map(this::createDefinitions)
                .forEach(this::createFile);

        Loader.instance().getActiveModList()
                .parallelStream()
                .forEach(this::moveFiles);

        sender.sendMessage(new TextComponentString("Finished Generating Files"));
    }

    private void moveFiles(ModContainer modContainer) {
        File minescribePath = new File(MineScribe.INSTANCE.getMinecraftFolder(), "minescribe");
        CraftingHelper.findFiles(modContainer, String.format("assets/%s/json_schemas", modContainer.getModId()),
                (root) -> true,
                (root, file) -> {
                    File minescribeFilePath = new File(minescribePath, file.toString().replace(root.toString(), ""));
                    if ("json".equals(FilenameUtils.getExtension(file.toString()))) {
                        BaseFileUtils.createFile(minescribeFilePath);
                        try (BufferedReader reader = Files.newBufferedReader(file);
                             BufferedWriter writer = Files.newBufferedWriter(minescribeFilePath.toPath())) {

                            String readLine;
                            while ((readLine = reader.readLine()) != null) {
                                writer.write(readLine);
                                writer.newLine();
                            }
                            writer.flush();
                        } catch (IOException e) {
                            MineScribe.INSTANCE.getLogger().error(e);
                        }
                    }

                    return true;
                }, true, true);

    }

    private Definitions createDefinitions(Map.Entry<String, Map<String, Definition>> definition) {
        return new Definitions(String.format("/minescribe/%s.json", definition.getKey()), definition.getValue());
    }

    private void createFile(Definitions definitions) {
        BaseFileUtils.writeStringToFile(gson.toJson(definitions), new File(MineScribe.INSTANCE.getMinecraftFolder(), definitions.id));
    }
}
