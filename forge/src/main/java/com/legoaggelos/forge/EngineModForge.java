package com.legoaggelos.forge;

import com.legoaggelos.EngineMod;

import immersive_aircraft.forge.CommonForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;


@Mod(EngineMod.MOD_ID)
public final class EngineModForge {

    public EngineModForge() {
        new CommonForge();
        EngineMod.init();
    }
}
