package com.teamacronymcoders.minescribe.mod;

import com.teamacronymcoders.base.BaseModFoundation;
import com.teamacronymcoders.base.command.CommandSubBase;
import com.teamacronymcoders.minescribe.mod.command.GenerateFolderCommand;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(
        modid = MineScribe.MOD_ID,
        name = MineScribe.MOD_NAME,
        version = MineScribe.VERSION
)
public class MineScribe extends BaseModFoundation<MineScribe> {
    public static final String MOD_ID = "minescribe";
    public static final String MOD_NAME = "MineScribe";
    public static final String VERSION = "1.0.0";

    @Instance(MOD_ID)
    public static MineScribe INSTANCE;

    public MineScribe() {
        super(MOD_ID, MOD_NAME, VERSION, CreativeTabs.MISC);
    }

    @Override
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }

    @Override
    @EventHandler
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    @Override
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

    @EventHandler
    public void serverInit(FMLServerStartingEvent event) {
        CommandSubBase minescribeCommand = new CommandSubBase("minescribe");
        minescribeCommand.addSubcommand(new GenerateFolderCommand());
        event.registerServerCommand(minescribeCommand);
    }

    @Override
    public MineScribe getInstance() {
        return this;
    }

    @Override
    public boolean hasExternalResources() {
        return true;
    }
}
